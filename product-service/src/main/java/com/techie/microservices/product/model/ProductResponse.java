package com.techie.microservices.product.model;

public record ProductResponse(long id,
                              String name,
                              String description,
                              Integer price,
                              Integer stockQuantity,
                              Boolean isActive,
                              String createdTime,
                              String updatedTime
                         ) {
}
