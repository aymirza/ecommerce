package com.example.ecommerce.repository;

import com.example.ecommerce.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);

    List<ProductCategory> findAllByOrderByCategoryType();

    ProductCategory findByCategoryType(Integer categoryType);

}
