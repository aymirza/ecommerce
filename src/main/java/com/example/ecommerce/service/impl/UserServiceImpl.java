package com.example.ecommerce.service.impl;

import com.example.ecommerce.enums.ResultEnum;
import com.example.ecommerce.exception.MyException;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;

    @Override
    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Collection<User> findByRole(String role) {
        return userRepository.finsdAllByRole(role);
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setPassword((passwordEncoder.encode(user.getPassword())));
        try {
            User savedUser = userRepository.save(user);

            Cart savedCart=cartRepository.save(new Cart(savedUser));
            savedUser.setCart(savedCart);
            return userRepository.save(savedUser);
        }
        catch (Exception e){
            throw  new MyException(ResultEnum.VALID_ERROR);

        }
    }

    @Override
    @Transactional
    public User update(User user) {
        User oldUser = userRepository.findByEmail(user.getEmail());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setName(user.getName());;
        oldUser.setPhone(user.getPhone());
        oldUser.setAddress(user.getAddress());
        return userRepository.save(oldUser);
    }
}
