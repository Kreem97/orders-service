package com.kareemhunte.assessment.ordersservice.controller;

import com.kareemhunte.assessment.ordersservice.controller.request.CreateOrderRequest;
import com.kareemhunte.assessment.ordersservice.controller.request.OrderResponse;
import com.kareemhunte.assessment.ordersservice.model.Order;
import com.kareemhunte.assessment.ordersservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        Order order = orderService.createOrder(createOrderRequest.getApples(), createOrderRequest.getOranges());
        OrderResponse orderResponse = OrderResponse.builder()
                .apples(order.getApples())
                .oranges(order.getOranges())
                .cost(order.getCost())
                .build();
        return ResponseEntity.ok(orderResponse);
    }
}
