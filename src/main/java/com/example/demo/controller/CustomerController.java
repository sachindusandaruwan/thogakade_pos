package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Save Customer
    @PostMapping
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO dto) {
        return customerService.saveCustomer(dto);
    }

    // Get All Customers
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get Customer By ID
    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    // Update Customer
    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(
            @PathVariable String id,
            @RequestBody CustomerDTO dto) {

        return customerService.updateCustomer(id, dto);
    }

    // Delete Customer
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable String id) {

        customerService.deleteCustomer(id);

        return "Customer deleted successfully";
    }
}