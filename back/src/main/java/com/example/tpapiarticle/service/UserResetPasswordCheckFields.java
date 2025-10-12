package com.example.tpapiarticle.service;

import com.example.tpapiarticle.entity.User;

public class UserResetPasswordCheckFields {
    public static boolean checkFields(User user) {
        return user.email == null || user.email.trim().isEmpty();
    }
}
