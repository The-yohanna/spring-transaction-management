package com.johanna.stm.dto;

import com.johanna.stm.model.Order;
import com.johanna.stm.model.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    private Order order;
    private Payment payment;

}
