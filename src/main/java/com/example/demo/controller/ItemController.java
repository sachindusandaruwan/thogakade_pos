package com.example.demo.controller;

import com.example.demo.dto.ItemDTO;
import com.example.demo.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Save Item
    @PostMapping
    public ItemDTO saveItem(@RequestBody ItemDTO dto) {
        return itemService.saveItem(dto);
    }

    // Get All Items
    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    // Get Item By ID
    @GetMapping("/{id}")
    public ItemDTO getItemById(@PathVariable String id) {
        return itemService.getItemById(id);
    }

    // Update Item
    @PutMapping("/{id}")
    public ItemDTO updateItem(
            @PathVariable String id,
            @RequestBody ItemDTO dto) {

        return itemService.updateItem(id, dto);
    }

    // Delete Item
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable String id) {

        itemService.deleteItem(id);

        return "Item deleted successfully";
    }
}