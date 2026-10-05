package com.example.demo.service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Save Customer
    public CustomerDTO saveCustomer(CustomerDTO dto) {

        Customer customer = new Customer();

        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setPhone(dto.getPhone());

        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerDTO(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getAddress(),
                savedCustomer.getPhone()
        );
    }

    // Get All Customers
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerDTO(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getPhone()
                ))
                .collect(Collectors.toList());
    }

    // Get Customer By ID
    public CustomerDTO getCustomerById(String id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getPhone()
        );
    }

    // Update Customer
    public CustomerDTO updateCustomer(String id, CustomerDTO dto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setPhone(dto.getPhone());

        Customer updatedCustomer = customerRepository.save(customer);

        return new CustomerDTO(
                updatedCustomer.getId(),
                updatedCustomer.getName(),
                updatedCustomer.getAddress(),
                updatedCustomer.getPhone()
        );
    }

    // Delete Customer
    public void deleteCustomer(String id) {

        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }

        customerRepository.deleteById(id);
    }
}