package com.example.cms.controller.exceptions;

public class CruiseNotFoundException extends RuntimeException{
    public CruiseNotFoundException(String code) {
        super("Could not find cruise " + code);
    }
}
