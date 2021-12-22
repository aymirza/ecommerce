package com.example.ecommerce.repository;

import com.example.ecommerce.models.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {
}
