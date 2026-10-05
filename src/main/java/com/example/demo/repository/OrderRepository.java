package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

}

/*
Order → අපි database එකේ handle කරන Entity එක
String → Order ID එකේ data type එක
 */