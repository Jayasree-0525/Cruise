package com.example.cms.controller.exceptions;

public class SurveyNotFoundException extends RuntimeException{
    public SurveyNotFoundException(Long id) {
        super("Could not find survey " + id);
    }
}
