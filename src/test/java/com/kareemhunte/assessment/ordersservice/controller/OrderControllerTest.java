package com.kareemhunte.assessment.ordersservice.controller;

import com.kareemhunte.assessment.ordersservice.model.Order;
import com.kareemhunte.assessment.ordersservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void givenInvalidCreateOrderRequest_WhenCreateOrder_ExpectBadRequest() throws Exception {
        mockMvc.perform(post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"apples\": -1, \"oranges\": 2}"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"apples\": 2, \"oranges\": -1}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenValidCreateOrderRequest_WhenCreateOrder_ExpectOkAndOrderResponse() throws Exception {
        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .apples(2)
                .oranges(3)
                .cost(1.95)
                .build();
        when(orderService.createOrder(2, 3)).thenReturn(order);

        mockMvc.perform(post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"apples\": 2, \"oranges\": 3}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(order.getId()))
                .andExpect(jsonPath("$.apples").value(2))
                .andExpect(jsonPath("$.oranges").value(3))
                .andExpect(jsonPath("$.cost").value(1.95));

        verify(orderService).createOrder(2, 3);
    }

    @Test
    void whenGetAllOrders_ExpectOkAndOrderResponseList() throws Exception {
        Order order1 = Order.builder()
                .apples(1)
                .oranges(2)
                .cost(1.1)
                .build();
        Order order2 = Order.builder()
                .apples(3)
                .oranges(4)
                .cost(2.2)
                .build();
        when(orderService.getAllOrders()).thenReturn(List.of(order1, order2));

        mockMvc.perform(get("/v1/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].apples").value(1))
                .andExpect(jsonPath("$[0].oranges").value(2))
                .andExpect(jsonPath("$[0].cost").value(1.1))
                .andExpect(jsonPath("$[1].apples").value(3))
                .andExpect(jsonPath("$[1].oranges").value(4))
                .andExpect(jsonPath("$[1].cost").value(2.2));

        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    void givenInvalidOrderId_WhenGetOrderById_ExpectNotFound() throws Exception {
        mockMvc.perform(get("/v1/orders/invalidId"))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenValidOrderId_WhenGetOrderById_ExpectOkAndOrderResponse() throws Exception {
        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .apples(2)
                .oranges(3)
                .cost(1.95)
                .build();
        when(orderService.getOrderById(order.getId())).thenReturn(order);

        mockMvc.perform(get("/v1/orders/" + order.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.apples").value(2))
                .andExpect(jsonPath("$.oranges").value(3))
                .andExpect(jsonPath("$.cost").value(1.95));

        verify(orderService).getOrderById(order.getId());
    }
}
