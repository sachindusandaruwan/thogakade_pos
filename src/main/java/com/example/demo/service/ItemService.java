package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

   public Optional<Item> findItemById(String id) {
        return itemRepository.findById(id);
   }


   public List<Item> findAllItems() {
        return itemRepository.findAll();
   }

   public void deleteItem(String id) {
       Item existingItem=itemRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Item not found"));

       itemRepository.delete(existingItem);
   }
}
