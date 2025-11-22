package com.techie.mocroservices.order.service;

import com.techie.mocroservices.order.model.OrderRequest;

public interface OrderService {

    void placeOrder(OrderRequest orderRequest);

}
