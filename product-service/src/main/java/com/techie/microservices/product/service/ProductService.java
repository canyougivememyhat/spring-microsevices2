package com.techie.microservices.product.service;

import com.techie.microservices.product.model.ProductRequest;
import com.techie.microservices.product.model.ProductResponse;

public interface ProductService {
    long createProduct(ProductRequest request);

    ProductResponse getProductById(long id);

    void updateProduct(long id, ProductRequest request);

    void deleteProduct(long id);
}
