package com.kareemhunte.assessment.ordersservice.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Order {
    String id;
    private int apples;
    private int oranges;
    private double cost;
}
