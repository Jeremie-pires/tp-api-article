package com.example.tpapiarticle.service;

import com.example.tpapiarticle.entity.User;

public class UserSignupCheckFields {
    public static boolean checkFields(User user) {
        return user.email == null || user.password == null || user.pseudo == null || user.city == null ||user.cityCode == null || user.phone == null
                || user.email.trim().isEmpty() || user.password.trim().isEmpty() || user.pseudo.trim().isEmpty()
                || user.city.trim().isEmpty() || user.cityCode.trim().isEmpty() || user.phone.trim().isEmpty();
    }
}
