package com.johanna.stm.service.impl;

import com.johanna.stm.dto.OrderRequest;
import com.johanna.stm.dto.OrderResponse;
import com.johanna.stm.exception.PaymentException;
import com.johanna.stm.model.Order;
import com.johanna.stm.model.Payment;
import com.johanna.stm.repository.OrderRepository;
import com.johanna.stm.repository.PaymentRepository;
import com.johanna.stm.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            PaymentRepository paymentRepository
    ) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderRequest.getOrder();
        order.setStatus("IN PROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if (!payment.getType().equals("DEBIT")) {
            throw new PaymentException("Card payment type not supported!");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");

        return orderResponse;
    }
}
