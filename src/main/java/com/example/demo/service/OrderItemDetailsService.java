package com.example.demo.service;

import com.example.demo.dto.OrderItemDetailsDTO;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItemDetails;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderItemDetailsRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemDetailsService {

    private final OrderItemDetailsRepository orderItemDetailsRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public OrderItemDetailsService(
            OrderItemDetailsRepository orderItemDetailsRepository,
            OrderRepository orderRepository,
            ItemRepository itemRepository) {

        this.orderItemDetailsRepository = orderItemDetailsRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    // Save Order Item Details
    public OrderItemDetailsDTO saveOrderItemDetails(OrderItemDetailsDTO dto) {

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() ->
                        new RuntimeException("Item not found"));

        OrderItemDetails details = new OrderItemDetails();

        details.setQuantity(dto.getQuantity());
        details.setOrder(order);
        details.setItem(item);

        OrderItemDetails savedDetails =
                orderItemDetailsRepository.save(details);

        return new OrderItemDetailsDTO(
                savedDetails.getQuantity(),
                savedDetails.getOrder().getOrderId(),
                savedDetails.getItem().getItemId()
        );
    }

    // Get All Order Item Details
    public List<OrderItemDetailsDTO> getAllOrderItemDetails() {

        return orderItemDetailsRepository.findAll()
                .stream()
                .map(details -> new OrderItemDetailsDTO(
                        details.getQuantity(),
                        details.getOrder().getOrderId(),
                        details.getItem().getItemId()
                ))
                .collect(Collectors.toList());
    }

    // Get By ID
    public OrderItemDetailsDTO getOrderItemDetailsById(Long id) {

        OrderItemDetails details = orderItemDetailsRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order Item Details not found"));

        return new OrderItemDetailsDTO(
                details.getQuantity(),
                details.getOrder().getOrderId(),
                details.getItem().getItemId()
        );
    }

    //update
    // Update Order Item Details
    public OrderItemDetailsDTO updateOrderItemDetails(
            Long id,
            OrderItemDetailsDTO dto) {

        OrderItemDetails details = orderItemDetailsRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order Item Details not found"));

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() ->
                        new RuntimeException("Item not found"));

        details.setQuantity(dto.getQuantity());
        details.setOrder(order);
        details.setItem(item);

        OrderItemDetails updatedDetails =
                orderItemDetailsRepository.save(details);

        return new OrderItemDetailsDTO(
                updatedDetails.getQuantity(),
                updatedDetails.getOrder().getOrderId(),
                updatedDetails.getItem().getItemId()
        );
    }


    // Delete
    public void deleteOrderItemDetails(Long id) {

        if (!orderItemDetailsRepository.existsById(id)) {
            throw new RuntimeException("Order Item Details not found");
        }

        orderItemDetailsRepository.deleteById(id);
    }
}