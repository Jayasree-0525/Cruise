package com.example.cms.controller;

import com.example.cms.controller.dto.ResponseDto;
import com.example.cms.controller.exceptions.SurveyNotFoundException;
import com.example.cms.controller.exceptions.ResponseNotFoundException;
import com.example.cms.controller.exceptions.QuestionNotFoundException;
import com.example.cms.model.entity.Survey;
import com.example.cms.model.entity.Question;
import com.example.cms.model.entity.Response;
import com.example.cms.model.entity.ResponseKey;
import com.example.cms.model.repository.QuestionRepository;
import com.example.cms.model.repository.SurveyRepository;
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
    private SurveyRepository surveyRepository;

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

    @PutMapping("/marks/{surveyId}/{questionId}")
    Response updateResponse(@RequestBody ResponseDto responseDto, @PathVariable("surveyId") int surveyId, @PathVariable("questionId") int questionId) {
        return repository.findById(new ResponseKey(surveyId, questionId))
                .map(response -> {
                    response.setResponseId(new ResponseKey(responseDto.getSurveyId(), responseDto.getQuestionId()));

                    Survey survey = surveyRepository.findById(reponseDto.getSurveyId()).orElseThrow(
                            () -> new com.example.cms.controller.SurveyNotFoundException(reponseDto.getSurveyId()));
                    response.setSurvey(survey);

                    Question question = questionRepository.findById(reponseDto.getQuestionId()).orElseThrow(
                            () -> new QuestionNotFoundException(reponseDto.getQuestionId()));
                    response.setQuestion(question);

                    response.setReponse(reponseDto.getResponse());

                    return repository.save(response);


                })

                .orElseGet(() -> {
                    Response response = new Response();
                    response.setResponseId(new ResponseKey(responseDto.getSurveyId(), responseDto.getQuestionId()));

                    Survey survey = surveyRepository.findById(reponseDto.getSurveyId()).orElseThrow(
                            () -> new SurveyNotFoundException(reponseDto.getSurveyId()));
                    response.setSurvey(survey);

                    Question question = questionRepository.findById(reponseDto.getQuestionId()).orElseThrow(
                            () -> new QuestionNotFoundException(reponseDto.getQuestionId()));
                    response.setQuestion(question);

                    response.setReponse(reponseDto.getResponse());

                    return repository.save(response);
                });
    }



}
