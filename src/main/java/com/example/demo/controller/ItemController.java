package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public Item SaveItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }

    @GetMapping("/{id}")
    public Optional<Item> findItemById(@PathVariable String id) {
        return itemService.findItemById(id);
    }

    @GetMapping
    public List<Item> findAllItems() {
        return itemService.findAllItems();
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable String id) {
         itemService.deleteItem(id);
         return "item deleted successfully";
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable String id, @RequestBody Item item) {
        return itemService.updateItem(id,item);
    }
}

