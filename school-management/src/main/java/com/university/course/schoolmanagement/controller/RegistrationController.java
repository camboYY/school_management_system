package com.university.course.schoolmanagement.controller;

import com.university.course.schoolmanagement.entity.User;
import com.university.course.schoolmanagement.entity.VerificationToken;
import com.university.course.schoolmanagement.event.RegistrationCompleteEvent;
import com.university.course.schoolmanagement.exception.BadUserVerificationException;
import com.university.course.schoolmanagement.exception.InvalidTokenException;
import com.university.course.schoolmanagement.exception.PasswordNotMatchException;
import com.university.course.schoolmanagement.model.PasswordModel;
import com.university.course.schoolmanagement.model.UserModel;
import com.university.course.schoolmanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/")
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser (@RequestBody UserModel userModel, final HttpServletRequest request) {
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/verifyRegistration")
    public ResponseEntity<String> verifyRegistration (@RequestParam("token") String token ) throws BadUserVerificationException {
        boolean validToken = userService.verifyRegistrationToken(token);
        if(validToken) {
            return new ResponseEntity<String>("User has been successfully verified", HttpStatus.OK);
        }
        throw new BadUserVerificationException();
    }

    @GetMapping("/resendVerifyToken")
    public ResponseEntity<String> resendVerifyToken (@RequestParam("token") String token, final HttpServletRequest request) {

        VerificationToken verificationToken = userService.resendVerifyToken(token);
        User user = verificationToken.getUser();
        resendVerificationTokenSend(token, applicationUrl(request), verificationToken);
        return new ResponseEntity<String>("Link have been sent", HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword (@RequestBody PasswordModel passwordModel, final HttpServletRequest request) {
        User user = userService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        if(user != null) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user,token);
            url = passwordResetTokenMail(user, applicationUrl(request), token);
        }
        return new ResponseEntity<String>(url, HttpStatus.OK);

    }
    @PostMapping("/savePassword")
    public ResponseEntity<String> savePassword (@RequestParam("token") String token, @RequestBody PasswordModel passwordModel) {
        boolean result = userService.validatePasswordResetToken(token);
        log.info("reset password mail Invalid token " +result +"result"+  token +"|"+ passwordModel);

        if(!result){
            throw new InvalidTokenException("Invalid Token");
        }

        Optional<User> user = userService.getUserByPasswordResetToken(token);
        log.info("save password mail: savePassword " +  user);
        if(user.isPresent()) {
            userService.changePassword(user.get(), passwordModel.getNewPassword());
            return new ResponseEntity<String>( "Reset password has been made", HttpStatus.OK);
        }
        throw new InvalidTokenException("Invalid Token");
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePasswordByEmail (@RequestBody PasswordModel passwordModel) {

        User user = userService.findUserByEmail(passwordModel.getEmail());

        if(!userService.checkIfOldPasswordIsValid(user, passwordModel)) throw new PasswordNotMatchException("Password is invalid");

        userService.changePassword(user, passwordModel.getNewPassword());
        return new ResponseEntity<>("Password has been changed", HttpStatus.OK);
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl+"savePassword?token="+token;
        log.info("reset password mail token " +  url);
        return url;
    }

    private void resendVerificationTokenSend(String token, String applicationURl, VerificationToken verificationToken) {
     String url = applicationURl+"verifyRegistration?token="+verificationToken.getToken();
     log.info("verify resend token " +  url);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+"/api/v1/users/"+request.getContextPath();
    }
}
