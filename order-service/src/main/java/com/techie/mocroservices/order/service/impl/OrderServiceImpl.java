package com.techie.mocroservices.order.service.impl;

import com.techie.mocroservices.order.client.InventoryClient;
import com.techie.mocroservices.order.model.Order;
import com.techie.mocroservices.order.model.OrderRequest;
import com.techie.mocroservices.order.repo.OrderRepository;
import com.techie.mocroservices.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        boolean isInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(isInStock){
            Order order = new Order();
            order.setId(orderRequest.id());
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);
        }else {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Product with SkuCode " + orderRequest.skuCode() + " is not in stock");
        }

    }

}
