package com.kareemhunte.assessment.ordersservice.service;

import com.kareemhunte.assessment.ordersservice.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {

    @Test
    void givenCreateOrderRequest_WhenCreateOrder_ExpectOrder() {
        // Arrange
        int apples = 1;
        int oranges = 2;
        double cost = (apples * 0.6) + (oranges * 0.25);

        // Act
        OrderService orderService = new OrderService();
        Order order = orderService.createOrder(apples, oranges);

        // Assert
        assertEquals(apples, order.getApples());
        assertEquals(oranges, order.getOranges());
        assertEquals(cost, order.getCost());
    }
}
