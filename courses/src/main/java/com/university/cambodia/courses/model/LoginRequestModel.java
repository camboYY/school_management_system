package com.university.cambodia.courses.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestModel {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
