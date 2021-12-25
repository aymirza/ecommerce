package com.example.ecommerce.magazin.service.impl;

import com.example.ecommerce.magazin.enums.ResultEnum;
import com.example.ecommerce.magazin.exception.MyException;
import com.example.ecommerce.magazin.models.Cart;
import com.example.ecommerce.magazin.models.User;
import com.example.ecommerce.magazin.repository.CartRepository;
import com.example.ecommerce.magazin.repository.UserRepository;
import com.example.ecommerce.magazin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Collection<User> findByRole(String role) {
        return userRepository.findAllByRole(role);
    }


    @Override
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            User savedUser = userRepository.save(user);

            Cart savedCart=cartRepository.save(new Cart(savedUser));
            savedUser.setCart(savedCart);
            return userRepository.save(user);
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
