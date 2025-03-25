package com.example.cms.controller.exceptions;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(int id) {
        super("Could not find question " + id);
    }
}
