package com.techie.microservices.product.service;

import com.techie.microservices.product.model.ProductRequest;
import com.techie.microservices.product.model.ProductResponse;

public interface ProductService {
    String createProduct(ProductRequest request);

    ProductResponse getProductById(String id);

    void updateProduct(String id, ProductRequest request);

    void deleteProduct(String id);
}
