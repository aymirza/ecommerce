package com.example.ecommerce.service;

import com.example.ecommerce.models.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderMain> findAll(Pageable pageable);
    Page<OrderMain> findByStatus(Integer status,Pageable pageable);
    Page<OrderMain> findByBuyerEmail(String email, Pageable pageable);
    Page<OrderMain> findByBuyerPhone(String phone,Pageable pageable);
    OrderMain findOne(Long orderId);
    OrderMain finish(Long orderId);
    OrderMain cencel(Long orderId);

}
