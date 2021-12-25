package com.example.ecommerce.magazin.service;

import com.example.ecommerce.magazin.models.User;

import java.util.Collection;

public interface UserService {
    User findOne(String email);

    Collection<User> findByRole(String role);

    User save(User user);

    User update(User user);
}
