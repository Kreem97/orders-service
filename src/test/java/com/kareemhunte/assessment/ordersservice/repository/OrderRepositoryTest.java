package com.kareemhunte.assessment.ordersservice.repository;

import com.kareemhunte.assessment.ordersservice.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderRepositoryTest {

    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = new OrderRepository();
    }

    @Test
    void givenOrder_WhenSaveOrder_ExpectOrderSaved() {
        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .apples(1)
                .oranges(2)
                .cost(1.1)
                .build();

        orderRepository.saveOrder(order);

        Order savedOrder = orderRepository.getOrder(order.getId());
        assertEquals(order, savedOrder);
    }

    @Test
    void whenGetAllOrders_ExpectAllOrders() {
        Order order1 = Order.builder()
                .id(UUID.randomUUID().toString())
                .build();
        Order order2 = Order.builder()
                .id(UUID.randomUUID().toString())
                .build();

        orderRepository.saveOrder(order1);
        orderRepository.saveOrder(order2);

        List<Order> orders = orderRepository.getAllOrders();
        assertEquals(2, orders.size());
        assertTrue(orders.contains(order1));
        assertTrue(orders.contains(order2));
    }

    @Test
    void givenOrderId_WhenGetOrder_ExpectOrder() {
        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .build();
        orderRepository.saveOrder(order);

        Order savedOrder = orderRepository.getOrder(order.getId());

        assertEquals(order, savedOrder);
    }
}
