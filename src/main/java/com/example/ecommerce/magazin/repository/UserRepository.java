package com.example.ecommerce.magazin.repository;

import com.example.ecommerce.magazin.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);
    Collection<User> findAllByRole(String role);

}
