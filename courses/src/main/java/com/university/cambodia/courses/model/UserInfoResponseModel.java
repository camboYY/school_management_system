package com.university.cambodia.courses.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoResponseModel {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
