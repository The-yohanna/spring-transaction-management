package com.johanna.stm.controller;

import com.johanna.stm.dto.OrderRequest;
import com.johanna.stm.dto.OrderResponse;
import com.johanna.stm.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrders(@RequestBody OrderRequest orderRequest) {

        return ResponseEntity.ok(orderService.placeOrder(orderRequest));

    }
}
