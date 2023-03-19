package com.ytecomm.jwt.service;

import com.ytecomm.jwt.entity.User;
import com.ytecomm.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User registerNewUser(User user){
    return userRepository.save(user);
    }
}
