package com.vitalsport.registration.service;


import com.vitalsport.registration.exception.IncorrectEmailException;
import com.vitalsport.registration.exception.IncorrectNickNameException;
import com.vitalsport.registration.exception.UserNotFoundException;
import com.vitalsport.registration.model.Account;

public interface AccountService {
    boolean isEmailFree(String email) throws IncorrectEmailException;
    boolean isLoginFree(String nickName) throws IncorrectNickNameException;

    String saveAccount(Account account) throws UserNotFoundException, IncorrectNickNameException, IncorrectEmailException;
    String login(Account account) throws UserNotFoundException, IncorrectNickNameException, IncorrectEmailException;
}
