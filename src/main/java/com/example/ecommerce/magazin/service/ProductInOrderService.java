package com.example.ecommerce.magazin.service;

import com.example.ecommerce.magazin.models.ProductInOrder;
import com.example.ecommerce.magazin.models.User;

public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);
    ProductInOrder findOne(String itemId, User user);
}
