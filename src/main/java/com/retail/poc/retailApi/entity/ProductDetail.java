package com.retail.poc.retailApi.entity;


import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public class ProductDetail {

    @Id
    String productId;
    String name;
    PriceDetail current_price;
}
