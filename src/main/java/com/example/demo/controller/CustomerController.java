package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // CREATE
    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        return customerService.saveCustomer(customer);
    }

    // GET ALL
    @GetMapping
    public List<Customer> getAllCustomers() {
        System.out.println("ggggg");
        return customerService.getAllCustomers();
    }

    // GET ONE
    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id) {
        return customerService.getCustomer(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable String id) {

        customerService.deleteCustomer(id);

        return "Customer deleted successfully";
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody Customer customer) {

        return customerService.updateCustomer(id,customer);
    }
}



/*
@RequestMapping("/api/customers")

So our base URL is:

http://localhost:8080/api/customers
 */




/*
Controller
    ↓
CustomerService
    ↓
CustomerRepository
    ↓
MySQL Database
*/