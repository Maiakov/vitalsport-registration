package com.vitalsport.registration.exception;

public class IncorrectEmailException extends Exception {

    @Override
    public String getMessage() {
        return "Incorrect email";
    }
}
