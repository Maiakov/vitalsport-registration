package com.vitalsport.registration.exception;

public class IncorrectNickNameException extends Exception {

    @Override
    public String getMessage() {
        return "Incorrect nick name";
    }
}
