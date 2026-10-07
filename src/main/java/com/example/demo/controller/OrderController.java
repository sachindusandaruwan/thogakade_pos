package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Save Order
    @PostMapping
    public OrderDTO saveOrder(@RequestBody OrderDTO dto) {
        return orderService.saveOrder(dto);
    }

    // Get All Orders
    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Get Order By ID
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }

    // Update Order
    @PutMapping("/{id}")
    public OrderDTO updateOrder(
            @PathVariable String id,
            @RequestBody OrderDTO dto) {

        return orderService.updateOrder(id, dto);
    }

    // Delete Order
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable String id) {

        orderService.deleteOrder(id);

        return "Order deleted successfully";
    }
}