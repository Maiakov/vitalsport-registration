package com.vitalsport.registration.service;


import com.vitalsport.registration.exception.IncorrectEmailException;
import com.vitalsport.registration.exception.IncorrectNickNameException;
import com.vitalsport.registration.exception.UserNotFoundException;
import com.vitalsport.registration.model.Account;

public interface AccountService {
    boolean isLoginFree(String email);
    String saveAccount(Account account) throws UserNotFoundException, IncorrectNickNameException, IncorrectEmailException;
    String login(Account account) throws UserNotFoundException, IncorrectNickNameException, IncorrectEmailException;
}
