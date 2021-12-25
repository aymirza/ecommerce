package com.example.ecommerce.magazin.controller;

import com.example.ecommerce.magazin.models.ProductCategory;
import com.example.ecommerce.magazin.models.ProductInfo;
import com.example.ecommerce.magazin.service.CategoryService;
import com.example.ecommerce.magazin.service.ProductService;
import com.example.ecommerce.magazin.vo.response.CategoryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/category/{type}")
    public CategoryPage showOne(@PathVariable("type") Integer categoryType,
                                @RequestParam(value = "page",defaultValue = "1") Integer page,
                                @RequestParam(value = "size",defaultValue = "3") Integer size){
        ProductCategory cat = categoryService.findByCategoryType(categoryType);
        PageRequest request = PageRequest.of(page-1,size);
        Page<ProductInfo> productInCategory = productService.findAllInCategory(categoryType,request);
        var tmp = new CategoryPage("",productInCategory);
        tmp.setCategory(cat.getCategoryName());
        return tmp;
    }
}
