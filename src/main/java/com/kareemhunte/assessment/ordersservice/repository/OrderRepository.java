package com.kareemhunte.assessment.ordersservice.repository;

import com.kareemhunte.assessment.ordersservice.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    private final Map<String, Order> orders = new HashMap<>();

    public void saveOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public Order getOrder(String id) {
        return orders.get(id);
    }
}
