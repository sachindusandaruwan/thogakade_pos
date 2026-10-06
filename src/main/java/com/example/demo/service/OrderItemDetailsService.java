package com.example.demo.service;

import com.example.demo.dto.OrderItemDetailsDTO;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItemDetails;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderItemDetailsRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public OrderItemDetailsDTO saveOrderItemDetails(OrderItemDetailsDTO dto) {

        // Find Order
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        // Find Item
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() ->
                        new RuntimeException("Item not found"));

        // Check Stock
        if (item.getQuantity() < dto.getQuantity()) {
            throw new RuntimeException("Not enough stock");
        }

        // Reduce Item Stock
        item.setQuantity(item.getQuantity() - dto.getQuantity());

        // Save updated Item
        itemRepository.save(item);

        // Create Order Item Details
        OrderItemDetails details = new OrderItemDetails();

        details.setQuantity(dto.getQuantity());
        details.setOrder(order);
        details.setItem(item);

        // Save Order Item Details
        OrderItemDetails savedDetails =
                orderItemDetailsRepository.save(details);

        // Return DTO
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

    // Get Order Item Details By ID
    public OrderItemDetailsDTO getOrderItemDetailsById(Long id) {

        OrderItemDetails details =
                orderItemDetailsRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Order Item Details not found"));

        return new OrderItemDetailsDTO(
                details.getQuantity(),
                details.getOrder().getOrderId(),
                details.getItem().getItemId()
        );
    }

    // Update Order Item Details
    @Transactional
    public OrderItemDetailsDTO updateOrderItemDetails(
            Long id,
            OrderItemDetailsDTO dto) {

        // Find existing order item details
        OrderItemDetails details =
                orderItemDetailsRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Order Item Details not found"));

        // Find Order
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        // Find Item
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() ->
                        new RuntimeException("Item not found"));

        // Old quantity
        int oldQuantity = details.getQuantity();

        // New quantity
        int newQuantity = dto.getQuantity();

        // Difference
        int difference = newQuantity - oldQuantity;

        // Check stock only when new quantity is greater
        if (difference > 0 && item.getQuantity() < difference) {
            throw new RuntimeException("Not enough stock");
        }

        // Update stock
        item.setQuantity(item.getQuantity() - difference);

        // Save Item
        itemRepository.save(item);

        // Update Order Item Details
        details.setQuantity(newQuantity);
        details.setOrder(order);
        details.setItem(item);

        // Save updated details
        OrderItemDetails updatedDetails =
                orderItemDetailsRepository.save(details);

        return new OrderItemDetailsDTO(
                updatedDetails.getQuantity(),
                updatedDetails.getOrder().getOrderId(),
                updatedDetails.getItem().getItemId()
        );
    }

    @Transactional
    public void deleteOrderItemDetails(Long id) {

        // Find Order Item Details
        OrderItemDetails details =
                orderItemDetailsRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Order Item Details not found"));

        // Get Item
        Item item = details.getItem();

        // Get sold quantity
        int quantity = details.getQuantity();

        // Restore stock
        item.setQuantity(item.getQuantity() + quantity);

        // Save updated item
        itemRepository.save(item);

        // Delete Order Item Details
        orderItemDetailsRepository.delete(details);
    }
}