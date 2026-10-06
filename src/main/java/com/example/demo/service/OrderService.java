package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(
            OrderRepository orderRepository,
            CustomerRepository customerRepository) {

        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    // Save Order
    @Transactional
    public OrderDTO saveOrder(OrderDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        var order = new com.example.demo.entity.Order();

        order.setOrderId(dto.getOrderId());
        order.setTotal(dto.getTotal());
        order.setCustomer(customer);

        var savedOrder = orderRepository.save(order);

        return new OrderDTO(
                savedOrder.getOrderId(),
                savedOrder.getTotal(),
                savedOrder.getCustomer().getId()
        );
    }

    // Get All Orders
    public List<OrderDTO> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(order -> new OrderDTO(
                        order.getOrderId(),
                        order.getTotal(),
                        order.getCustomer().getId()
                ))
                .collect(Collectors.toList());
    }

    // Get Order By ID
    public OrderDTO getOrderById(String orderId) {

        var order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        return new OrderDTO(
                order.getOrderId(),
                order.getTotal(),
                order.getCustomer().getId()
        );
    }

    // Update Order
    public OrderDTO updateOrder(String orderId, OrderDTO dto) {

        var order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        order.setTotal(dto.getTotal());
        order.setCustomer(customer);

        var updatedOrder = orderRepository.save(order);

        return new OrderDTO(
                updatedOrder.getOrderId(),
                updatedOrder.getTotal(),
                updatedOrder.getCustomer().getId()
        );
    }

    // Delete Order
    public void deleteOrder(String orderId) {

        if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Order not found");
        }

        orderRepository.deleteById(orderId);
    }
}