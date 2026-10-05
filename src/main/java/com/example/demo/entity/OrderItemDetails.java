package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id") //Many OrderItems → One Order
    /*Order O001
   ↑
   |
   ├── OrderItem 1
   ├── OrderItem 2
   └── OrderItem 3
   */
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id") //Many OrderItems → One Item
    /*
    Item I001 = Rice

     ↑
     |
 ┌───┼────┐
 |   |    |
OI1 OI5  OI9

*/
    @JsonIgnore
    private Item item;

    public OrderItemDetails() {

    }

    public OrderItemDetails(int quantity, Order order, Item item) {
        this.quantity = quantity;
        this.order = order;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}