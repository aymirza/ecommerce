package com.example.ecommerce.service.impl;

import com.example.ecommerce.enums.ResultEnum;
import com.example.ecommerce.exception.MyException;
import com.example.ecommerce.models.OrderMain;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductInOrderRepository;
import com.example.ecommerce.repository.ProductInfoRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductInfoRepository productInfoRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    public Page<OrderMain> findAll(Pageable pageable) {
        return orderRepository.findAllByOrderByOrderStatusAscCreateTimeDesc(pageable);
    }

    @Override
    public Page<OrderMain> findByStatus(Integer status, Pageable pageable) {
        return orderRepository.findAllByOrderStatusOrderByCreateTimeDesc(status,pageable);
    }

    @Override
    public Page<OrderMain> findByBuyerEmail(String email, Pageable pageable) {
        return orderRepository.findAllByBuyerEmailOrderStatusAscCreateTimeDesc(email,pageable);
    }

    @Override
    public Page<OrderMain> findByBuyerPhone(String phone, Pageable pageable) {
        return orderRepository.findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(phone,pageable);
    }

    @Override
    public OrderMain findOne(Long orderId) {
        OrderMain orderMain = orderRepository.findByOrderId(orderId);
        if (orderMain == null){
            throw new MyException(ResultEnum.ORDER_NOT_FOUND);
        }
        return orderMain;
    }

    @Override
    public OrderMain finish(Long orderId) {
        return null;
    }

    @Override
    public OrderMain cencel(Long orderId) {
        return null;
    }
}
