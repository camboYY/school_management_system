package com.university.cambodia.courses.service;

import com.university.cambodia.courses.entity.AppUser;
import com.university.cambodia.courses.entity.Role;

import java.util.List;


public interface AppUserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
