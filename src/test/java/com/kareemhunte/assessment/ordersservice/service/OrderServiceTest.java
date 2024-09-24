package com.kareemhunte.assessment.ordersservice.service;

import com.kareemhunte.assessment.ordersservice.model.Order;
import com.kareemhunte.assessment.ordersservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void givenCreateOrderRequest_WhenCreateOrder_ExpectOrder() {
        // Arrange
        int apples = 1;
        int oranges = 2;
        double cost = (apples * 0.6) + (oranges * 0.25);

        // Act
        Order order = orderService.createOrder(apples, oranges);

        // Assert
        verify(orderRepository, times(1)).saveOrder(order);
        assertDoesNotThrow(() -> UUID.fromString(order.getId())); // check if id is a valid UUID
        assertEquals(apples, order.getApples());
        assertEquals(oranges, order.getOranges());
        assertEquals(cost, order.getCost());
    }

    @Test
    void whenGetAllOrders_ExpectAllOrders() {
        // Arrange
        Order order1 = Order.builder()
                .id(UUID.randomUUID().toString())
                .build();
        Order order2 = Order.builder()
                .id(UUID.randomUUID().toString())
                .build();
        when(orderRepository.getAllOrders()).thenReturn(List.of(order1, order2));

        // Act
        List<Order> orders = orderService.getAllOrders();

        // Assert
        assertEquals(2, orders.size());
        assertEquals(order1, orders.get(0));
        assertEquals(order2, orders.get(1));
    }

    @Test
    void givenOrder_WhenGetOrderById_ExpectOrder() {
        // Arrange
        Order expectedOrder = Order.builder()
                .id(UUID.randomUUID().toString())
                .build();
        when(orderRepository.getOrder(expectedOrder.getId())).thenReturn(expectedOrder);

        // Act
        Order actualOrder = orderService.getOrderById(expectedOrder.getId());

        // Assert
        assertEquals(expectedOrder, actualOrder);
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
