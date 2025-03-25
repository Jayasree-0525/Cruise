package com.example.cms.controller.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String code) {
        super("Could not find customer " + code);
    }
}
