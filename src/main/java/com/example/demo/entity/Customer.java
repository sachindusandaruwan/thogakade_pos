package com.example.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="customer")
public class Customer {
    @Id
    private String id;
    private String name;
    private String address;
    private int phone;



    public Customer() {

    }
    public Customer(String id, String name, String address, int phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
}

