package com.example.tpapiarticle.service;

import com.example.tpapiarticle.entity.User;

public class UserLoginCheckFields {
    public static boolean checkFields(User user) {
        return user.email == null || user.password == null || user.email.trim().isEmpty() || user.password.trim().isEmpty();
    }
}
