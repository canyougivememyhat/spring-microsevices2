package com.techie.microservices.product.model;

public record ProductRequest(String name,
                             String description,
                             Integer price,
                             Integer stockQuantity) {
}
