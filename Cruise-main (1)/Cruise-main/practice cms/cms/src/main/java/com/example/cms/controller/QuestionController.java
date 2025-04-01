package com.example.cms.controller;

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

    @GetMapping("/questions")
    List<Question> retrieveAllQuestions() {
        return repository.findAll();
    }

    @PostMapping("/questions")
    Question createQuestion(@RequestBody Question newQuestion) {
        return repository.save(newQuestion);
    }

    @DeleteMapping("/questions/{id}")
    void deleteQuestion(@PathVariable("id") int questionId) {
        responseRepository.deleteResponsesBySingleQuestionId(questionId);
        repository.deleteById(questionId);
    }

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
