package com.example.cms.controller;

import com.example.cms.controller.dto.SurveyDto;
import com.example.cms.controller.exceptions.CruiseNotFoundException;
import com.example.cms.controller.exceptions.CustomerNotFoundException;
import com.example.cms.controller.exceptions.SurveyNotFoundException;
import com.example.cms.model.entity.Cruise;
import com.example.cms.model.entity.Customer;
import com.example.cms.model.entity.Survey;
import com.example.cms.model.repository.CustomerRepository;
import com.example.cms.model.repository.ResponseRepository;
import com.example.cms.model.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class CustomerController {

    @Autowired
    private final CustomerRepository repository;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private ResponseRepository responseRepository;


    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    //Get all customers
    @GetMapping("/customers")
    List<Customer> retrieveAllCustomers() {
        return repository.findAll();
    }

    //Get specific customer based on Id
    @GetMapping("/customers/{id}")
    Customer retrieveCustomer(@PathVariable("id") int customerId) {
        return repository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    //Create a new customer
    @PostMapping("/customers")
    Customer createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = new Customer();
        newCustomer.setId(customer.getId());
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setAge(customer.getAge());
        newCustomer.setGender(customer.getGender());
        return repository.save(newCustomer);
    }

    //Delete customer
    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable("id") int customerId) {
        //delete all responses related to customers first
        List<Integer> surveysIdsByCustomerId = responseRepository.findSurveyIdsByCustomerId(customerId);
        responseRepository.deleteResponsesBySurveyIds(surveysIdsByCustomerId);
        //delete all surveys related to customer next
        surveyRepository.deleteSurveyByCustomerId(customerId);
        //finally delete customer after removing dependencies
        repository.deleteById(customerId);
    }

    //Edit customer details
    @PutMapping("/customers/{id}")
    Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable("id") int customerId){
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
