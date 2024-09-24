package com.kareemhunte.assessment.ordersservice.service;

import com.kareemhunte.assessment.ordersservice.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final double APPLE_PRICE = 0.60;
    private static final double ORANGE_PRICE = 0.25;

    public Order createOrder(int apples, int oranges) {
        double total = (apples * APPLE_PRICE) + (oranges * ORANGE_PRICE);

        return Order.builder()
                .apples(apples)
                .oranges(oranges)
                .cost(total)
                .build();
    }
}
