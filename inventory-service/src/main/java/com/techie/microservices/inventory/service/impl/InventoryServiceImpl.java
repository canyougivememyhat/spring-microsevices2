package com.techie.microservices.inventory.service.impl;

import com.techie.microservices.inventory.repo.InventoryRepository;
import com.techie.microservices.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }

}
