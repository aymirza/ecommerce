package com.example.ecommerce.repository;

import com.example.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);
    Collection<User> findAllByRole(String role);

}
