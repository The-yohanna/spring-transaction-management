package com.johanna.stm.service;

import com.johanna.stm.dto.OrderRequest;
import com.johanna.stm.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest orderRequest);

}
