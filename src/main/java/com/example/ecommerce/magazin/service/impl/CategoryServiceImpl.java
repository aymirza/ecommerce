package com.example.ecommerce.magazin.service.impl;

import com.example.ecommerce.magazin.enums.ResultEnum;
import com.example.ecommerce.magazin.exception.MyException;
import com.example.ecommerce.magazin.models.ProductCategory;
import com.example.ecommerce.magazin.service.CategoryService;
import com.example.ecommerce.magazin.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> res = productCategoryRepository.findAllByOrderByCategoryType();
        return res;
    }

    @Override
    public ProductCategory findByCategoryType(Integer categoryType) {
       ProductCategory res = productCategoryRepository.findByCategoryType(categoryType);
       if (res == null) throw new MyException(ResultEnum.ORDER_NOT_FOUND);
       return res;
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<ProductCategory> res = productCategoryRepository.findByCategoryTypeInOrderByCategoryTypeAsc(categoryTypeList);
        return res;
    }

    @Override
    @Transactional
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
