package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository=customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomer(String id) {
        return customerRepository.findById(id);
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

//    public Customer updateCustomer(String id, Customer customer) {
//        return customerRepository.save(customer);
//    }

    public Customer updateCustomer(String id, Customer customer) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        System.out.println(existingCustomer);
        existingCustomer.setName(customer.getName());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setPhone(customer.getPhone());

        return customerRepository.save(existingCustomer);
    }
}
