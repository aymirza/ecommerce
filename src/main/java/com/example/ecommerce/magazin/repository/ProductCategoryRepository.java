package com.example.ecommerce.magazin.repository;

import com.example.ecommerce.magazin.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);

    List<ProductCategory> findAllByOrderByCategoryType();

    ProductCategory findByCategoryType(Integer categoryType);

}
