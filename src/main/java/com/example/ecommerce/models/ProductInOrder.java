package com.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class ProductInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private OrderMain orderMain;
    @NotNull
    private String productId;
    @NotNull
    private String productName;
    @NotNull
    private String productDescription;
    @NotNull
    private String productIcon;
    @NotNull
    private Integer categoryType;
    @NotNull
    private BigDecimal productPrice;
    private Integer productStock;
    private Integer count;

    public ProductInOrder(ProductInfo productInfo,Integer quantity){
        this.productId=productInfo.getProductId();
        this.productName = productInfo.getProductName();
        this.productDescription=productInfo.getProductDescription();
        this.productIcon=productInfo.getProductIcon();
        this.categoryType=productInfo.getCategoryType();
        this.productPrice=productInfo.getProductPrice();
        this.productStock=productInfo.getProductStock();
        this.count=quantity;
    }








}
