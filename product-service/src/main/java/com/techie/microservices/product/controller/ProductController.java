package com.techie.microservices.product.controller;

import com.techie.microservices.product.model.ApiResponse;
import com.techie.microservices.product.model.ProductRequest;
import com.techie.microservices.product.model.ProductResponse;
import com.techie.microservices.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "2.1.1 新增商品", description = "新增商品")
    @PostMapping
    ResponseEntity<ApiResponse> createProduct(@RequestBody ProductRequest request){
        long productId = productService.createProduct(request);

        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("成功")
                .data(productId)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "2.1.2 依 ID 取得商品資訊", description = "依 ID 取得商品資訊")
    @GetMapping("/{id}")
    ResponseEntity<ApiResponse> showProductById(@PathVariable Integer id){
        ProductResponse product = productService.getProductById(id);

        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("成功")
                .data(product)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "2.1.3 依 ID 修改商品資訊", description = "依 ID 修改商品資訊")
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse> updateProductById(@PathVariable Integer id,
                                                  @RequestBody ProductRequest request){
        productService.updateProduct(id, request);

        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("成功")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "2.1.4 依 ID 刪除商品", description = "依 ID 刪除商品")
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteProductById(@PathVariable Integer id){
        productService.deleteProduct(id);

        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("成功")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(response);
    }

}
