package com.kareemhunte.assessment.ordersservice.controller.request;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateOrderRequest {
    @Min(value = 0, message = "Number of apples must be a positive whole number.")
    private int apples;
    @Min(value = 0, message = "Number of orange must be a positive whole number.")
    private int oranges;
}
