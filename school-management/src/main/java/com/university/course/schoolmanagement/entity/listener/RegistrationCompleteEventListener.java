package com.university.course.schoolmanagement.entity.listener;

import com.university.course.schoolmanagement.entity.User;
import com.university.course.schoolmanagement.event.RegistrationCompleteEvent;
import com.university.course.schoolmanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // Verify token from user link
        User user  = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(user, token);
        //Send mail to user
        String url = event.getApplicationUrl()+"verifyRegistration?token="+token;

        // send verification mail
        log.info("Click the link to verify your account : "+url);
    }
}
