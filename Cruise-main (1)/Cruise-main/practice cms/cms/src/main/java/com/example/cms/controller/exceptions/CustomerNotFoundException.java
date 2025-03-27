package com.example.cms.controller.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(int code) {
        super("Could not find customer " + code);
    }
}
