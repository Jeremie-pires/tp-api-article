package com.example.tpapiarticle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.tpapiarticle.entity.User;
import com.example.tpapiarticle.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> displayUsersList() {
        List<User> users = userService.showUserList();
        return users;
    }

}
