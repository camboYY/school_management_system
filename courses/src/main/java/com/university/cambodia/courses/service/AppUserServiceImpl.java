package com.university.cambodia.courses.service;

import com.university.cambodia.courses.entity.AppUser;
import com.university.cambodia.courses.entity.Role;
import com.university.cambodia.courses.repository.AppUserRepository;
import com.university.cambodia.courses.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    @Override
    public AppUser saveUser(AppUser user) {
        return appUserRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {}  to a user username: {}", roleName, username);

    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user by username {} ", username);
        return null;
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all roles");
        return appUserRepository.findAll();
    }
}
