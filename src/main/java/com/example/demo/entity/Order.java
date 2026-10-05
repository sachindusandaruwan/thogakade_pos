package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;

    private double total;

    @ManyToOne //Many Orders can belong to One Customer.
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItemDetails> orderItemDetails;

    public Order() {

    }

    public Order(String orderId, double total, Customer customer) {
        this.orderId = orderId;
        this.total = total;
        this.customer = customer;
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


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public List<OrderItemDetails> getOrderItemDetails() {
        return orderItemDetails;
    }

    public void setOrderItemDetails(List<OrderItemDetails> orderItemDetails) {
        this.orderItemDetails = orderItemDetails;
    }
}
