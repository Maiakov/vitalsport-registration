package com.vitalsport.registration.service;

import com.vitalsport.registration.exception.IncorrectEmailException;
import com.vitalsport.registration.exception.IncorrectNickNameException;
import com.vitalsport.registration.exception.UserNotFoundException;
import com.vitalsport.registration.model.Account;
import com.vitalsport.registration.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private static final int TOKEN_EXPIRATION_MMS = -1;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public boolean isEmailFree(String email) throws IncorrectEmailException {
        log.info("Searching account with email {}", email);
        Validator.validateEmail(email);
        Account existedAccount = accountRepository.findByEmail(email);
        return existedAccount == null;
    }

    @Override
    public boolean isLoginFree(String nickName) throws IncorrectNickNameException {
        log.info("Searching account with login {}", nickName);
        Validator.validateNickName(nickName);
        Account existedAccount = accountRepository.findByNickName(nickName);
        return existedAccount == null;
    }

    @Override
    public String saveAccount(Account account) throws UserNotFoundException, IncorrectNickNameException, IncorrectEmailException {
        log.info("Saving account {}", account);
        Validator.validateNickName(account.getNickName());
        Validator.validateEmail(account.getEmail());
        accountRepository.save(account);
        return login(account);
    }

    @Override
    public String login(Account account) throws UserNotFoundException, IncorrectNickNameException, IncorrectEmailException {
        log.info("Login with email {}", account.getEmail());
        Validator.validateNickName(account.getNickName());
        Validator.validateEmail(account.getEmail());
        Account existedAccount = accountRepository.findByEmailAndPasswordOrNickNameAndPassword(account.getEmail(), account.getPassword());
        if (existedAccount == null || existedAccount.getEmail() == null || existedAccount.getNickName() == null) {
            throw new UserNotFoundException();
        }
        return tokenGenerator.createJWT(existedAccount.getNickName(), String.valueOf(existedAccount.getId()), TOKEN_EXPIRATION_MMS);
    }

}
