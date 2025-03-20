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

    @GetMapping("/surveys")
    List<Survey> retrieveAllSurveys() {
        return repository.findAll();
    }

    @PostMapping("/survey")
    Survey createSurvey(@RequestBody Survey newSurvey) {
        return repository.save(newSurvey);
    }


    /*@PostMapping("/courses")
    Course createCourse(@RequestBody CourseDto courseDto) {
        Course newCourse = new Course();
        newCourse.setName(courseDto.getName());
        newCourse.setCode(courseDto.getCode());
        Professor professor = professorRepository.findById(courseDto.getProfessorId()).orElseThrow(
                () -> new ProfessorNotFoundException(courseDto.getProfessorId()));
        newCourse.setProfessor(professor);
        return repository.save(newCourse);
    }*/

    @DeleteMapping("/surveys/{id}")
    void deleteSurvey(@PathVariable("id") String surveyId) {repository.deleteById(surveyId);}

}
