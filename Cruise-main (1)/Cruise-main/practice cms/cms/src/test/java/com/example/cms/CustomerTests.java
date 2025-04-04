package com.example.cms;

import com.example.cms.model.entity.Survey;
import com.example.cms.model.repository.CustomerRepository;

import com.example.cms.model.repository.SurveyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.cms.model.entity.Customer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

class CustomerTests {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void addCustomers() throws Exception{

        ObjectNode customerJson = objectMapper.createObjectNode();
        customerJson.put("id", 8888);
        customerJson.put("firstName", "Bob");
        customerJson.put("lastName", "Blake");
        customerJson.put("email", "bob.blake@email.com");
        customerJson.put("age", 31);
        customerJson.put("gender", "male");


        MockHttpServletResponse response = mockMvc.perform(
                        post("/customers").
                                contentType("application/json").
                                content(customerJson.toString()))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        assertTrue(customerRepository.findById(8888).isPresent());
        Customer addedCustomer = customerRepository.findById(8888).get();

        assertEquals(8888, addedCustomer.getId());
        assertEquals("Bob", addedCustomer.getFirstName());
        assertEquals("Blake", addedCustomer.getLastName());
        assertEquals("bob.blake@email.com", addedCustomer.getEmail());
        assertEquals(31, addedCustomer.getAge());
        assertEquals("male", addedCustomer.getGender());
    }
    @Test
    void getCustomers() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(get("/customers/8888"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ObjectNode receivedJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals(8888, receivedJson.get("id").intValue());
        assertEquals("Bob", receivedJson.get("firstName").textValue());
        assertEquals("Blake", receivedJson.get("lastName").textValue());
        assertEquals("bob.blake@email.com", receivedJson.get("email").textValue());
        assertEquals(31, receivedJson.get("age").intValue());
        assertEquals("male", receivedJson.get("gender").textValue());
    }

    @Test
    void deleteCustomers() throws Exception{

        Customer c = new Customer();
        c.setId(6);
        c.setFirstName("Nikita");
        c.setLastName("Shashidhar");
        c.setEmail("nikita.shashidhar@email.com");
        c.setAge(20);
        c.setGender("female");
        customerRepository.save(c);

        MockHttpServletResponse response = mockMvc.perform(
                        delete("/customers/6").
                                contentType("application/json"))
                .andReturn().getResponse();


        assertEquals(200, response.getStatus());
        assertTrue(customerRepository.findById(6).isEmpty());
    }

    @Test
    void updateCustomers() throws Exception {

        ObjectNode customerJsonUpdated = objectMapper.createObjectNode();
        customerJsonUpdated.put("id", 8888);
        customerJsonUpdated.put("firstName", "Bob");
        customerJsonUpdated.put("lastName", "Blake");
        customerJsonUpdated.put("email", "bob.blake@email.com");
        customerJsonUpdated.put("age", 34);
        customerJsonUpdated.put("gender", "male");


        MockHttpServletResponse response2 = mockMvc.perform(
                        //perform an update aka put instead of post
                        put("/customers/8888").
                                contentType("application/json").
                                content(customerJsonUpdated.toString()))
                .andReturn().getResponse();

        assertEquals(200, response2.getStatus());

        assertTrue(customerRepository.findById(8888).isPresent());
        Customer updatedCustomer = customerRepository.findById(8888).get();

        assertEquals(8888, updatedCustomer.getId());
        assertEquals("Bob", updatedCustomer.getFirstName());
        assertEquals("Blake", updatedCustomer.getLastName());
        assertEquals("bob.blake@email.com", updatedCustomer.getEmail());
        assertEquals(34, updatedCustomer.getAge());
        assertEquals("male", updatedCustomer.getGender());
    }


}
