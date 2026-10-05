package com.example.demo.service;

import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Save Item
    public ItemDTO saveItem(ItemDTO dto) {

        Item item = new Item();

        item.setItemId(dto.getItemId());
        item.setItemName(dto.getItemName());
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());

        Item savedItem = itemRepository.save(item);

        return new ItemDTO(
                savedItem.getItemId(),
                savedItem.getItemName(),
                savedItem.getQuantity(),
                savedItem.getPrice()
        );
    }

    // Get All Items
    public List<ItemDTO> getAllItems() {

        return itemRepository.findAll()
                .stream()
                .map(item -> new ItemDTO(
                        item.getItemId(),
                        item.getItemName(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());
    }

    // Get Item By ID
    public ItemDTO getItemById(String itemId) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() ->
                        new RuntimeException("Item not found"));

        return new ItemDTO(
                item.getItemId(),
                item.getItemName(),
                item.getQuantity(),
                item.getPrice()
        );
    }

    // Update Item
    public ItemDTO updateItem(String itemId, ItemDTO dto) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() ->
                        new RuntimeException("Item not found"));

        item.setItemName(dto.getItemName());
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());

        Item updatedItem = itemRepository.save(item);

        return new ItemDTO(
                updatedItem.getItemId(),
                updatedItem.getItemName(),
                updatedItem.getQuantity(),
                updatedItem.getPrice()
        );
    }

    // Delete Item
    public void deleteItem(String itemId) {

        if (!itemRepository.existsById(itemId)) {
            throw new RuntimeException("Item not found");
        }

        itemRepository.deleteById(itemId);
    }
}