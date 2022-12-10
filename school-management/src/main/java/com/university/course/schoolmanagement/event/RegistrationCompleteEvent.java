package com.university.course.schoolmanagement.event;

import com.university.course.schoolmanagement.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private  User user;
    private String applicationUrl;

    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.applicationUrl = applicationUrl;
        this.user = user;
    }

}
