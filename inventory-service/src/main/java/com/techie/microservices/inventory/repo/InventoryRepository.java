package com.techie.microservices.inventory.repo;

import com.techie.microservices.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);

}
