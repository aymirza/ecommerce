package com.example.ecommerce.repository;

import com.example.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);
    Collection<User> finsdAllByRole(String role);

}
