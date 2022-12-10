package com.auth.resource.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @GetMapping("/")
    public String [] getUser () {
        return new String[]{"Shabbir", "Nikhil", "Shivam"};
    }
}
