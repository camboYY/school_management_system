package com.university.course.schoolmanagement.service;

import com.university.course.schoolmanagement.entity.User;
import com.university.course.schoolmanagement.entity.VerificationToken;
import com.university.course.schoolmanagement.model.PasswordModel;
import com.university.course.schoolmanagement.model.UserModel;

import java.util.Optional;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(User user, String token);

    boolean verifyRegistrationToken(String token);

    VerificationToken resendVerifyToken(String token);

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    boolean validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean checkIfOldPasswordIsValid(User user, PasswordModel passwordModel);
}
