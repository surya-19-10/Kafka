package com.order.dto;

import lombok.Data;

@Data
public class Order {
    private int orderId;
    private String name;
    private double cost;
    private String category;
}
