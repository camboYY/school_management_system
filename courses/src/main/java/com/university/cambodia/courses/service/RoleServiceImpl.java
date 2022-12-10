package com.university.cambodia.courses.service;

import com.university.cambodia.courses.entity.Role;
import com.university.cambodia.courses.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void addRoles(List<Role> roles) {
        List<Role> list = new ArrayList<>();
        roles.forEach(role -> {
            list.add(role);
        });
        roleRepository.saveAll(list);
    }
}
