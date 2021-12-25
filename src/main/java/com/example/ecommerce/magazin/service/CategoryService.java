package com.example.ecommerce.magazin.service;

import com.example.ecommerce.magazin.models.ProductCategory;

import java.util.List;

public interface CategoryService {
    List<ProductCategory> findAll();
    ProductCategory findByCategoryType(Integer categoryType);
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory save(ProductCategory productCategory);

}
