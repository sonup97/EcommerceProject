package com.ytecomm.jwt.service;

import com.ytecomm.jwt.entity.Role;
import com.ytecomm.jwt.entity.User;
import com.ytecomm.jwt.repository.RoleRepository;
import com.ytecomm.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    public User registerNewUser(User user){
    return userRepository.save(user);}

    public void initRolesAndUser(){
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setRoleName("User");
            userRole.setRoleDescription("Default role for newly created record");
            roleRepository.save(userRole);

            User adminUser = new User();
            adminUser.setUserFirstName("admin");
            adminUser.setUserLastName("admin");
            adminUser.setUserName("admin123");
            adminUser.setUserPassword("admin@pass");
            Set<Role> adminRoles =new HashSet<>();
            adminRoles.add(adminRole);
//            System.out.println(adminRoles);
            adminUser.setRole(adminRoles);
            userRepository.save(adminUser);

            User user = new User();
            user.setUserFirstName("sonu");
            user.setUserLastName("pandit");
            user.setUserName("sonu123");
            user.setUserPassword("sonu@pass");
            Set<Role> userRoles =new HashSet<>();
            userRoles.add(userRole);
            user.setRole(userRoles);
            userRepository.save(user);



        }
}
