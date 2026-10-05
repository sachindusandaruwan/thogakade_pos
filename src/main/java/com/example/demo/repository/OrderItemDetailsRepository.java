package com.example.demo.repository;

import com.example.demo.entity.OrderItemDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDetailsRepository extends JpaRepository<OrderItemDetails, Long> {

}

/*
JpaRepository<OrderItemDetails, Long>
OrderItemDetails → අපි database එකේ manage කරන Entity එක
Long → OrderItemDetails එකේ id type එක
 */