package com.techie.mocroservices.order.service.impl;

import com.techie.mocroservices.order.model.Order;
import com.techie.mocroservices.order.model.OrderRequest;
import com.techie.mocroservices.order.repo.OrderRepository;
import com.techie.mocroservices.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setId(orderRequest.id());
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());
        orderRepository.save(order);
    }

}
