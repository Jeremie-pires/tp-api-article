package com.example.tpapiarticle.controller;

import com.example.tpapiarticle.service.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.tpapiarticle.entity.User;
import com.example.tpapiarticle.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users/login")
    public ResponseDTO login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/users/signup")
    public ResponseDTO signup(@RequestBody User user) {
        return userService.signup(user);
    }

    @PostMapping("/users/reset-password")
    public ResponseDTO resetPassword(@RequestBody User user) {
        return userService.resetPassword(user);
    }

}
