package com.example.ecommerce.magazin.service;

import com.example.ecommerce.magazin.models.Cart;
import com.example.ecommerce.magazin.models.ProductInOrder;
import com.example.ecommerce.magazin.models.User;

import java.util.Collection;

public interface CartService {
    Cart getCart(User user);
    void mergeLocalCart(Collection<ProductInOrder> productInOrder,User user);
    void delete(String itemId,User user);
    void checkout(User user);

}
