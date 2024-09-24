package com.kareemhunte.assessment.ordersservice.controller;

import com.kareemhunte.assessment.ordersservice.model.Order;
import com.kareemhunte.assessment.ordersservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                .apples(2)
                .oranges(3)
                .cost(1.95)
                .build();
        when(orderService.createOrder(2, 3)).thenReturn(order);

        mockMvc.perform(post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"apples\": 2, \"oranges\": 3}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.apples").value(2))
                .andExpect(jsonPath("$.oranges").value(3))
                .andExpect(jsonPath("$.cost").value(1.95));
    }
}
