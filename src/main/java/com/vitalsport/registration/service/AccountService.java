package com.vitalsport.registration.service;


import com.vitalsport.registration.exeption.UserNotFoundException;
import com.vitalsport.registration.model.Account;

public interface AccountService {
    boolean isLoginFree(String email);
    void saveAccount(Account account);
    String login(Account account) throws UserNotFoundException;
}
