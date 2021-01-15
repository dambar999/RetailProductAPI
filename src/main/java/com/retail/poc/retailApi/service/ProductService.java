package com.retail.poc.retailApi.service;

import com.retail.poc.retailApi.entity.ProductDetail;

public interface ProductService {

    String  getProductName(String productId);
    ProductDetail generateProductDetail(String productId);
}
