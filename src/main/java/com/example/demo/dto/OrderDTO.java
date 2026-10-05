package com.example.demo.dto;

public class OrderDTO {

    private String orderId;
    private double total;
    private String customerId;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, double total, String customerId) {
        this.orderId = orderId;
        this.total = total;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}