/*package com.example.cms.controller;

import com.example.cms.model.entity.Survey;
import com.example.cms.model.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SurveyController {

    @Autowired
    private final SurveyRepository repository;

    public SurveyController(SurveyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/survey")
    List<Survey> retrieveAllRecords() {
        return repository.findAll(); //used student entity as the template for this
    }




}*/

package com.example.cms.controller;

import com.example.cms.controller.dto.ResponseDto;
import com.example.cms.controller.dto.SurveyDto;
import com.example.cms.controller.exceptions.CruiseNotFoundException;
import com.example.cms.controller.exceptions.CustomerNotFoundException;
import com.example.cms.controller.exceptions.QuestionNotFoundException;
import com.example.cms.controller.exceptions.SurveyNotFoundException;
import com.example.cms.model.entity.*;
import com.example.cms.model.repository.CruiseRepository;
import com.example.cms.model.repository.CustomerRepository;
import com.example.cms.model.repository.ResponseRepository;
import com.example.cms.model.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SurveyController {
    @Autowired
    private final SurveyRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CruiseRepository cruiseRepository;

    @Autowired
    private ResponseRepository responseRepository;


    public SurveyController(SurveyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/surveys")
    List<Survey> retrieveAllSurveys() {
        return repository.findAll();
    }

    @GetMapping("/surveys/{id}")
    Survey retrieveSurvey(@PathVariable("id") int surveyId) {
        return repository.findById(surveyId)
                .orElseThrow(() -> new SurveyNotFoundException(surveyId));
    }

    @PostMapping("/surveys")
    Survey createSurvey(@RequestBody SurveyDto surveyDto) {
        Survey newSurvey = new Survey();
        newSurvey.setSurveyId(surveyDto.getSurveyId());
        Customer customer = customerRepository.findById(surveyDto.getCustomerId()).orElseThrow(
                () -> new CustomerNotFoundException(surveyDto.getCustomerId()));
        newSurvey.setCustomer(customer);
        Cruise cruise = cruiseRepository.findById(surveyDto.getCruiseId()).orElseThrow(
                () -> new CruiseNotFoundException(surveyDto.getCruiseId()));
        newSurvey.setCruise(cruise);
        newSurvey.setDateOfSurvey(surveyDto.getDateOfSurvey());
        return repository.save(newSurvey);
    }

    @DeleteMapping("/surveys/{id}")
    void deleteSurvey(@PathVariable("id") int surveyId) {
        responseRepository.deleteResponsesBySingleSurveyId(surveyId);
        repository.deleteById(surveyId);
    }

    @PutMapping("/surveys/{id}")
    Survey updateSurvey(@RequestBody SurveyDto surveyDto, @PathVariable("id") int surveyId) {
        return repository.findById(surveyId)
                .map(survey -> {
                    survey.setSurveyId(surveyDto.getSurveyId());
                    Customer customer = customerRepository.findById(surveyDto.getCustomerId()).orElseThrow(
                            () -> new CustomerNotFoundException(surveyDto.getCustomerId()));
                    survey.setCustomer(customer);

                    Cruise cruise = cruiseRepository.findById(surveyDto.getCruiseId()).orElseThrow(
                            () -> new CruiseNotFoundException(surveyDto.getCruiseId()));
                    survey.setCruise(cruise);
                    survey.setDateOfSurvey(surveyDto.getDateOfSurvey());
                    return repository.save(survey);
                })
                .orElseGet(() -> {
                    Survey newSurvey = new Survey();
                    newSurvey.setSurveyId(surveyDto.getSurveyId());
                    Customer customer = customerRepository.findById(surveyDto.getCustomerId()).orElseThrow(
                            () -> new CustomerNotFoundException(surveyDto.getCustomerId()));
                    newSurvey.setCustomer(customer);
                    Cruise cruise = cruiseRepository.findById(surveyDto.getCruiseId()).orElseThrow(
                            () -> new CruiseNotFoundException(surveyDto.getCruiseId()));
                    newSurvey.setCruise(cruise);
                    newSurvey.setDateOfSurvey(surveyDto.getDateOfSurvey());
                    return repository.save(newSurvey);
                });
    }

}
