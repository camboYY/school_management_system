package com.university.course.schoolmanagement.service;

import com.university.course.schoolmanagement.entity.PasswordResetToken;
import com.university.course.schoolmanagement.entity.User;
import com.university.course.schoolmanagement.entity.VerificationToken;
import com.university.course.schoolmanagement.exception.EmailNotFoundException;
import com.university.course.schoolmanagement.model.PasswordModel;
import com.university.course.schoolmanagement.model.UserModel;
import com.university.course.schoolmanagement.repository.PasswordResetTokenRepository;
import com.university.course.schoolmanagement.repository.UserRepository;
import com.university.course.schoolmanagement.repository.VerificationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole("USER");
        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public boolean verifyRegistrationToken(String token) {
        VerificationToken tokenVerified = verificationTokenRepository.findByToken(token);
        if (tokenVerified == null) return false;

        User user = tokenVerified.getUser();
        Calendar calendar = Calendar.getInstance();

        if((tokenVerified.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            verificationTokenRepository.delete(tokenVerified);
            return false;
        }

        user.setEnabled(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public VerificationToken resendVerifyToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)  throw new EmailNotFoundException("Email is not found");
        return user;
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(user,token);
        passwordResetTokenRepository.save(passwordResetToken);

    }

    @Override
    public boolean validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        log.info("User passwordResetToken :" + passwordResetToken);
        if (passwordResetToken == null) return false;

        Calendar calendar = Calendar.getInstance();

        if((passwordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            passwordResetTokenRepository.delete(passwordResetToken);
            return false;
        }

        return true;
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean checkIfOldPasswordIsValid(User user, PasswordModel passwordModel) {
        return passwordEncoder.matches(passwordModel.getOldPassword(), user.getPassword());
    }
}
