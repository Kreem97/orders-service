package com.kareemhunte.assessment.ordersservice.service;

import com.kareemhunte.assessment.ordersservice.model.Order;
import com.kareemhunte.assessment.ordersservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private static final double APPLE_PRICE = 0.60;
    private static final double ORANGE_PRICE = 0.25;

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(int apples, int oranges) {
        // calculate total cost
        double total = calculateApplesCost(apples) + calculateOrangesCost(oranges);

        // create and store order
        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .apples(apples)
                .oranges(oranges)
                .cost(total)
                .build();
        orderRepository.saveOrder(order);

        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Order getOrderById(String id) {
        return orderRepository.getOrder(id);
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
