package com.ytecomm.jwt.service;

import com.ytecomm.jwt.entity.Role;
import com.ytecomm.jwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    public Role createNewRole(Role role){
    return roleRepository.save(role);
    }
}
