package com.example.cms.controller;

import com.example.cms.model.entity.Response;
import com.example.cms.model.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class ResponseController {
    @Autowired
    private final ResponseRepository repository;

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
}
