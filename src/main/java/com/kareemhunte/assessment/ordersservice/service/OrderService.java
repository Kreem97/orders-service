package com.kareemhunte.assessment.ordersservice.service;

import com.kareemhunte.assessment.ordersservice.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final double APPLE_PRICE = 0.60;
    private static final double ORANGE_PRICE = 0.25;

    public Order createOrder(int apples, int oranges) {
        double total = calculateApplesCost(apples) + calculateOrangesCost(oranges);

        return Order.builder()
                .apples(apples)
                .oranges(oranges)
                .cost(total)
                .build();
    }

    public double calculateApplesCost(int apples) {
        // BOGO deal
        int applesToCharge = (apples + 1) / 2;
        return applesToCharge * APPLE_PRICE;
    }

    public double calculateOrangesCost(int oranges) {
        // 3 for the price of 2 deal
        int orangesToCharge = (oranges / 3) * 2 + (oranges % 3);
        return orangesToCharge * ORANGE_PRICE;
    }
}
