package com.techie.microservices.product.service.impl;

import com.techie.microservices.product.model.Product;
import com.techie.microservices.product.model.ProductRequest;
import com.techie.microservices.product.model.ProductResponse;
import com.techie.microservices.product.repo.ProductRepository;
import com.techie.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public String createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .stockQuantity(request.stockQuantity())
                .price(BigDecimal.valueOf(request.price()))
                .isActive(true)
                .createdAt(LocalDate.now())
                .build();

        return productRepository.save(product).getId();
    }

    @Override
    public ProductResponse getProductById(String id) {
        return convertToProductDto(productRepository.findById(id).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到 id="+id+" 的產品")
                )
        );
    }

    @Override
    public void updateProduct(String id, ProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到 id="+id+" 的產品")
        );

        if(request.name() != null) product.setName(request.name());
        if(request.description() != null) product.setDescription(request.description());
        if(request.price() != null) product.setPrice(BigDecimal.valueOf(request.price()));
        if(request.stockQuantity() != null) product.setStockQuantity(request.stockQuantity());
        product.setUpdatedAt(LocalDate.now());
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "找不到 id="+id+" 的產品")
        );

        productRepository.delete(product);
    }

    private ProductResponse convertToProductDto(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice().intValue(),
                product.getStockQuantity(),
                product.getIsActive(),
                product.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE),
                product.getUpdatedAt() == null ? null :
                        product.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE)
        );
    }

}
