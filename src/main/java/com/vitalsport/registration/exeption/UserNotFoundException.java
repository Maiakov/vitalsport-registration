package com.vitalsport.registration.exeption;

public class UserNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "User not found exception";
    }
}
