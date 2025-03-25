package com.example.cms.controller.exceptions;

public class ResponseNotFoundException extends RuntimeException{
    public ResponseNotFoundException(Long id) {
        super("Could not find response " + id);
    }
}
