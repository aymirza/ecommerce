package com.example.ecommerce.magazin.service.impl;

import com.example.ecommerce.magazin.models.ProductInOrder;
import com.example.ecommerce.magazin.models.User;
import com.example.ecommerce.magazin.repository.ProductInOrderRepository;
import com.example.ecommerce.magazin.service.ProductInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    public void update(String itemId, Integer quantity, User user) {
        var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCount(quantity);
            productInOrderRepository.save(productInOrder);
        });
    }

    @Override
    public ProductInOrder findOne(String itemId, User user) {
        var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        AtomicReference<ProductInOrder> res = new AtomicReference<>();
        op.ifPresent(res::set);
        return res.get();
    }
}
