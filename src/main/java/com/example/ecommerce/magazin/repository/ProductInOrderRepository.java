package com.example.ecommerce.magazin.repository;

import com.example.ecommerce.magazin.models.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {
}
