package com.example.demo.controller;

import com.example.demo.dto.OrderItemDetailsDTO;
import com.example.demo.service.OrderItemDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemDetailsController {

    private final OrderItemDetailsService orderItemDetailsService;

    public OrderItemDetailsController(
            OrderItemDetailsService orderItemDetailsService) {

        this.orderItemDetailsService = orderItemDetailsService;
    }

    // Save Order Item Details
    @PostMapping
    public OrderItemDetailsDTO saveOrderItemDetails(
            @RequestBody OrderItemDetailsDTO dto) {

        return orderItemDetailsService.saveOrderItemDetails(dto);
    }

    // Get All
    @GetMapping
    public List<OrderItemDetailsDTO> getAllOrderItemDetails() {

        return orderItemDetailsService.getAllOrderItemDetails();
    }

    // Get By ID
    @GetMapping("/{id}")
    public OrderItemDetailsDTO getOrderItemDetailsById(
            @PathVariable Long id) {

        return orderItemDetailsService.getOrderItemDetailsById(id);
    }

    //update
    // Update Order Item Details
    @PutMapping("/{id}")
    public OrderItemDetailsDTO updateOrderItemDetails(
            @PathVariable Long id,
            @RequestBody OrderItemDetailsDTO dto) {

        return orderItemDetailsService.updateOrderItemDetails(id, dto);
    }


    // Delete
    @DeleteMapping("/{id}")
    public String deleteOrderItemDetails(
            @PathVariable Long id) {

        orderItemDetailsService.deleteOrderItemDetails(id);

        return "Order Item Details deleted successfully";
    }
}