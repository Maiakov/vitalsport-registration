package com.vitalsport.registration.exception;

public class UserNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "User not found exception";
    }
}
