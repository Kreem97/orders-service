package com.kareemhunte.assessment.ordersservice.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderResponse {
    private String id;
    private int apples;
    private int oranges;
    private double cost;
}
