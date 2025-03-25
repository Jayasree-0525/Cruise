package com.example.cms.controller;

import com.example.cms.model.entity.Customer;
import com.example.cms.model.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class CustomerController {
    // test change for Nikita's github

    @Autowired
    private final CustomerRepository repository;
    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customers")
    List<Customer> retrieveAllCustomers() {
        return repository.findAll();
    }

    @PostMapping("/customer")
    Customer createCustomer(@RequestBody Customer newCustomer) {
        return repository.save(newCustomer);
    }

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable("id") String customerId) {repository.deleteById(customerId);}

    @PutMapping("/customers/{id}")
    Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable("id") String customerId){
        return repository.findById(customerId)
                .map(customer -> {
                    customer.setFirstName(newCustomer.getFirstName());
                    customer.setLastName(newCustomer.getLastName());
                    customer.setEmail(newCustomer.getEmail());
                    customer.setAge(newCustomer.getAge());
                    customer.setGender(newCustomer.getGender());
                    return repository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(customerId);
                    newCustomer.setFirstName(newCustomer.getFirstName());
                    newCustomer.setLastName(newCustomer.getLastName());
                    newCustomer.setEmail(newCustomer.getEmail());
                    newCustomer.setAge(newCustomer.getAge());
                    newCustomer.setGender(newCustomer.getGender());
                    return repository.save(newCustomer);
                });
    }
}
