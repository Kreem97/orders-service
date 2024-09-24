package com.kareemhunte.assessment.ordersservice.service;

import com.kareemhunte.assessment.ordersservice.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {

    private final OrderService orderService = new OrderService();

    @Test
    void givenCreateOrderRequest_WhenCreateOrder_ExpectOrder() {
        // Arrange
        int apples = 1;
        int oranges = 2;
        double cost = (apples * 0.6) + (oranges * 0.25);

        // Act
        Order order = orderService.createOrder(apples, oranges);

        // Assert
        assertEquals(apples, order.getApples());
        assertEquals(oranges, order.getOranges());
        assertEquals(cost, order.getCost());
    }

    @Test
    void givenApples_WhenCalculateApplesCost_ExpectCost() {
        assertEquals(0, orderService.calculateApplesCost(0));
        assertEquals(0.6, orderService.calculateApplesCost(1));
        assertEquals(0.6, orderService.calculateApplesCost(2));
        assertEquals(1.2, orderService.calculateApplesCost(3));
    }

    @Test
    void givenApples_WhenCalculateOrangesCost_ExpectCost() {
        assertEquals(0, orderService.calculateOrangesCost(0));
        assertEquals(0.50, orderService.calculateOrangesCost(2));
        assertEquals(0.50, orderService.calculateOrangesCost(3));
        assertEquals(1, orderService.calculateOrangesCost(5));
    }
}
