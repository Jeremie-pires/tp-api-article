package com.example.tpapiarticle.service;


import com.example.tpapiarticle.entity.User;
import com.example.tpapiarticle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> showUserList() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
