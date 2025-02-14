package com.datacenter.datateam.application.exceptions;

public class ParameterNotFoundException extends RuntimeException {
    public ParameterNotFoundException(String message) {
        super(message);
    }
}

