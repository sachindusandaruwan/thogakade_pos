package com.example.demo.dto;

public class OrderItemDetailsDTO {

    private int quantity;
    private String orderId;
    private String itemId;

    public OrderItemDetailsDTO() {
    }
    public OrderItemDetailsDTO(
            int quantity,
            String orderId,
            String itemId) {

        this.quantity = quantity;
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}