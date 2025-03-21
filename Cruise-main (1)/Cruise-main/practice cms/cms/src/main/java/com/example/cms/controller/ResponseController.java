package com.example.cms.controller;

import com.example.cms.controller.dto.ResponseDto;
import com.example.cms.controller.exceptions.CustomerNotFoundException;
import com.example.cms.controller.exceptions.ResponseNotFoundException;
import com.example.cms.controller.exceptions.QuestionNotFoundException;
import com.example.cms.model.entity.Customer;
import com.example.cms.model.entity.Question;
import com.example.cms.model.entity.Response;
import com.example.cms.model.entity.ResponseKey;
import com.example.cms.model.repository.CustomerRepository;
import com.example.cms.model.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class ResponseController {
    @Autowired
    private final ResponseRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseController(ResponseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/responses")
    List<Response> retrieveAllResponses() {
        return repository.findAll();
    }

    @PostMapping("/responses")
    Response createResponse(@RequestBody Response newResponse) {
        return repository.save(newResponse);
    }

    @DeleteMapping("/responses/{id}")
    void deleteResponse(@PathVariable("id") String responseId) {repository.deleteById(responseId);}

    @PutMapping("/responses/{id}")
    Response updateResponse(@RequestBody Response newResponse, @PathVariable("id") String responseId){
        return repository.findById(responseId)
                .map(response -> {
                    response.setResponse(newResponse.getResponse());
                    return repository.save(response);
                })
                .orElseGet(() -> {
                    newResponse.setId(responseId);
                    newResponse.setResponse(newResponse.getResponse());
                    return repository.save(newResponse);
                });
    }

    @PutMapping("/marks/{customerId}/{questionId}")
    Response updateResponse(@RequestBody ResponseDto responseDto, @PathVariable("customerId") int customerId, @PathVariable("questionId") int questionId) {
        return repository.findById(new ResponseKey(customerId, questionId))
                .map(response -> {
                    response.setResponseId(new ResponseKey(responseDto.getCustomerId(), responseDto.getQuestionId()));

                    Customer customer = customerRepository.findById(reponseDto.getCustomerId()).orElseThrow(
                            () -> new com.example.cms.controller.CustomerNotFoundException(reponseDto.getCustomerId()));
                    response.setCustomer(customer);

                    Question question = questionRepository.findById(reponseDto.getQuestionId()).orElseThrow(
                            () -> new QuestionNotFoundException(reponseDto.getQuestionId()));
                    response.setQuestion(question);

                    response.setReponse(reponseDto.getResponse());

                    return repository.save(response);


                })

                .orElseGet(() -> {
                    Response response = new Response();
                    response.setResponseId(new ResponseKey(responseDto.getCustomerId(), responseDto.getQuestionId()));

                    Customer customer = customerRepository.findById(reponseDto.getCustomerId()).orElseThrow(
                            () -> new CustomerNotFoundException(reponseDto.getCustomerId()));
                    response.setCustomer(customer);

                    Question question = questionRepository.findById(reponseDto.getQuestionId()).orElseThrow(
                            () -> new QuestionNotFoundException(reponseDto.getQuestionId()));
                    response.setQuestion(question);

                    response.setReponse(reponseDto.getResponse());

                    return repository.save(response);
                });
    }



}
