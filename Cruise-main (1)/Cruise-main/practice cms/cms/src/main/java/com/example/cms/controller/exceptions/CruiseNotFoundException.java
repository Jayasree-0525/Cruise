package com.example.cms.controller.exceptions;

public class CruiseNotFoundException extends RuntimeException{
    public CruiseNotFoundException(int code) {
        super("Could not find cruise " + code);
    }
}
