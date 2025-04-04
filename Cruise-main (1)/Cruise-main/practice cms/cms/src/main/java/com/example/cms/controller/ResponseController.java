package com.example.cms.controller;

import com.example.cms.controller.dto.ResponseDto;
import com.example.cms.controller.exceptions.QuestionNotFoundException;
import com.example.cms.controller.exceptions.ResponseNotFoundException;
import com.example.cms.controller.exceptions.SurveyNotFoundException;
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
import java.util.Optional;

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

    //see all responses
    @GetMapping("/responses")
    List<Response> retrieveAllResponses() {
        return repository.findAll();
    }

    //see specific response based on survey and question id
    @GetMapping("/responses/{surveyId}/{questionId}")
    Optional<Response> getResponse(@PathVariable("surveyId") int surveyId, @PathVariable("questionId") int questionId) {
        return repository.findById(new ResponseKey(surveyId, questionId));
    }

    //create/post a new response to a question
    @PostMapping("/responses")
    Response createResponse(@RequestBody ResponseDto responseDto) {
        Response newResponse = new Response();
        newResponse.setResponseId(new ResponseKey(responseDto.getSurveyId(), responseDto.getQuestionId()));
        Survey survey = surveyRepository.findById(responseDto.getSurveyId()).orElseThrow(
                () -> new SurveyNotFoundException(responseDto.getSurveyId()));
        newResponse.setSurvey(survey);
        Question question = questionRepository.findById(responseDto.getQuestionId()).orElseThrow(
                () -> new QuestionNotFoundException(responseDto.getQuestionId()));
        newResponse.setQuestion(question);
        newResponse.setResponse(responseDto.getResponse());
        return repository.save(newResponse);
    }

    //delete response
    @DeleteMapping("/responses/{surveyId}/{questionId}")
    void deleteResponse(@PathVariable("surveyId") int surveyId, @PathVariable("questionId") int questionId) {
        repository.deleteResponseByQuestionAndSurvey(questionId, surveyId);
    }

    //edit response
    @PutMapping("/responses/{surveyId}/{questionId}")
    Response updateResponse(@RequestBody ResponseDto responseDto, @PathVariable("surveyId") int surveyId, @PathVariable("questionId") int questionId) {
        return repository.findById(new ResponseKey(surveyId, questionId))
                .map(response -> {
                    response.setResponseId(new ResponseKey(responseDto.getSurveyId(), responseDto.getQuestionId()));
                    Survey survey = surveyRepository.findById(responseDto.getSurveyId()).orElseThrow(
                            () -> new SurveyNotFoundException(responseDto.getSurveyId()));
                    response.setSurvey(survey);
                    Question question = questionRepository.findById(responseDto.getQuestionId()).orElseThrow(
                            () -> new QuestionNotFoundException(responseDto.getQuestionId()));
                    response.setQuestion(question);
                    response.setResponse(responseDto.getResponse());
                    return repository.save(response);


                })

                .orElseGet(() -> {
                    Response response = new Response();
                    response.setResponseId(new ResponseKey(responseDto.getSurveyId(), responseDto.getQuestionId()));
                    Survey survey = surveyRepository.findById(responseDto.getSurveyId()).orElseThrow(
                            () -> new SurveyNotFoundException(responseDto.getSurveyId()));
                    response.setSurvey(survey);
                    Question question = questionRepository.findById(responseDto.getQuestionId()).orElseThrow(
                            () -> new QuestionNotFoundException(responseDto.getQuestionId()));
                    response.setQuestion(question);
                    response.setResponse(responseDto.getResponse());
                    return repository.save(response);
                });
    }

    //SQL queries to calculate/display collected data and analytics
    // quantitative - average
    @GetMapping("/responses/quant/average/{questionId}")
    float quant_avg(@PathVariable("questionId") int questionId) {
        return repository.averageFunc(questionId);
    }


    // quantitative - max
    @GetMapping("/responses/quant/max/{questionId}")
    float quant_max(@PathVariable("questionId") int questionId) {
        return repository.maxFunc(questionId);
    }


    // quantitative - min
    @GetMapping("/responses/quant/min/{questionId}")
    float quant_min(@PathVariable("questionId") int questionId) {
        return repository.minFunc(questionId);
    }


    // quantitative - count responses per question
    @GetMapping("/responses/quant/count/specific/{questionId}")
    int quant_count_specific(@PathVariable("questionId") int questionId) {
        return repository.specificCountFunc(questionId);
    }


    // quantitative - count total number of responses
    @GetMapping("/responses/quant/count/total")
    int quant_count_total() {
        return repository.totalCountFunc();
    }


    // quantitative - sample std dev
    @GetMapping("/responses/quant/sd/{questionId}")
    float quant_sd(@PathVariable("questionId") int questionId) {
        return repository.sdFunc(questionId);
    }


    // quantitative - avg based on question and cruise Id
    @GetMapping("/responses/quant/average/{questionId}/{cruiseId}")
    float avgQuestionCruise(@PathVariable("questionId") int questionId, @PathVariable("cruiseId") int cruiseId) {
        return repository.averageResponseByQuestionAndCruise(questionId, cruiseId);
    }

    // quantitative - min based on question and cruise Id
    @GetMapping("/responses/quant/min/{questionId}/{cruiseId}")
    float minQuestionCruise(@PathVariable("questionId") int questionId, @PathVariable("cruiseId") int cruiseId) {
        return repository.minResponseByQuestionAndCruise(questionId, cruiseId);
    }

    // quantitative - max based on question and cruise Id
    @GetMapping("/responses/quant/max/{questionId}/{cruiseId}")
    float maxQuestionCruise(@PathVariable("questionId") int questionId, @PathVariable("cruiseId") int cruiseId) {
        return repository.maxResponseByQuestionAndCruise(questionId, cruiseId);
    }

    // qualitative - search for instances of a word
    @GetMapping("/responses/qual/search/{searchstring}")
    List<Response> searchKeyWord(@PathVariable("searchstring") String searchString) {
        return repository.searchQual(searchString);
    }

    // qualitative - count rows containing a word
    @GetMapping("/responses/qual/countword/{searchstring}")
    int countResponsesWithWord(@PathVariable("searchstring") String searchString) {
        return repository.countQualWord(searchString);
    }



}
