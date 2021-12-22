package com.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class ProductInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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






}
