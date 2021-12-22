package com.example.ecommerce.service;

import com.example.ecommerce.models.ProductInOrder;
import com.example.ecommerce.models.User;

public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);
    ProductInOrder findOne(String itemId,User user);
}
