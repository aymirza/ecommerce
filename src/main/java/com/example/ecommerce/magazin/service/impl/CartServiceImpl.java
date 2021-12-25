package com.example.ecommerce.magazin.service.impl;

import com.example.ecommerce.magazin.enums.ResultEnum;
import com.example.ecommerce.magazin.exception.MyException;
import com.example.ecommerce.magazin.models.User;
import com.example.ecommerce.magazin.service.CartService;
import com.example.ecommerce.magazin.models.Cart;
import com.example.ecommerce.magazin.models.OrderMain;
import com.example.ecommerce.magazin.models.ProductInOrder;
import com.example.ecommerce.magazin.repository.CartRepository;
import com.example.ecommerce.magazin.repository.OrderRepository;
import com.example.ecommerce.magazin.repository.ProductInOrderRepository;
import com.example.ecommerce.magazin.repository.UserRepository;
import com.example.ecommerce.magazin.service.ProductService;
import com.example.ecommerce.magazin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductInOrderRepository productInOrderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserService userService;


    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }

    @Override
    @Transactional
    public void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user) {
        Cart finalCart = user.getCart();
        productInOrders.forEach(productInOrder -> {
            Set<ProductInOrder> set = finalCart.getProducts();
            Optional<ProductInOrder> old = set.stream().filter(e -> e.getProductId().equals(productInOrder.getProductId())).findFirst();
            ProductInOrder prod;
            if (old.isPresent()){
                prod = old.get();
                prod.setCount(productInOrder.getCount()+prod.getCount());
            }else {
                prod = productInOrder;
                prod.setCart(finalCart);
                finalCart.getProducts().add(prod);
            }
        });
    }

    @Override
    @Transactional
    public void delete(String itemId, User user) {
        if (itemId.equals("")||user ==null){
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }
        var op = user.getCart().getProducts().stream().filter(e->itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCart(null);
            productInOrderRepository.deleteById(productInOrder.getId());
        });
    }

    @Override
    @Transactional
    public void checkout(User user) {
        OrderMain order = new OrderMain(user);
        orderRepository.save(order);


        user.getCart().getProducts().forEach(productInOrder -> {
            productInOrder.setCart(null);
            productInOrder.setOrderMain(order);
            productService.decreaseStock(productInOrder.getProductId(),productInOrder.getCount());
            productInOrderRepository.save(productInOrder);
        });

    }
}
