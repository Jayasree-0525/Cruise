package com.example.cms.controller;

import com.example.cms.controller.exceptions.CustomerNotFoundException;
import com.example.cms.controller.exceptions.QuestionNotFoundException;
import com.example.cms.model.entity.Customer;
import com.example.cms.model.entity.Question;
import com.example.cms.model.repository.QuestionRepository;
import com.example.cms.model.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class QuestionController {

    @Autowired
    private final QuestionRepository repository;

    @Autowired
    private ResponseRepository responseRepository;

    public QuestionController(QuestionRepository repository) {
        this.repository = repository;
    }

    //see all the questions in the database
    @GetMapping("/questions")
    List<Question> retrieveAllQuestions() {
        return repository.findAll();
    }

    //see specific question based on id
    @GetMapping("/questions/{id}")
    Question retrieveQuestion(@PathVariable("id") int questionId) {
        return repository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException(questionId));
    }

    //create new question
    @PostMapping("/questions")
    Question createQuestion(@RequestBody Question newQuestion) {
        return repository.save(newQuestion);
    }

    //delete question
    @DeleteMapping("/questions/{id}")
    void deleteQuestion(@PathVariable("id") int questionId) {
        responseRepository.deleteResponsesBySingleQuestionId(questionId);
        repository.deleteById(questionId);
    }

    //update/edit a question
    @PutMapping("/questions/{id}")
    Question updateQuestion(@RequestBody Question newQuestion, @PathVariable("id") int questionId){
        return repository.findById(questionId)
                .map(question -> {
                    question.setQuestion(newQuestion.getQuestion());
                    question.setType(newQuestion.getType());
                    return repository.save(question);
                })
                .orElseGet(() -> {
                    newQuestion.setQuestionId(questionId);
                    newQuestion.setQuestion(newQuestion.getQuestion());
                    newQuestion.setType(newQuestion.getType());
                    return repository.save(newQuestion);
                });
    }

}
