package com.vitalsport.registration.service;

import com.vitalsport.registration.exeption.UserNotFoundException;
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
    public boolean isLoginFree(String email) {
        log.info("Searching account with login {}", email);
        Account existedAccount = accountRepository.findOne(email);
        return existedAccount == null;
    }

    @Override
    public void saveAccount(Account account) {
        log.info("Saving acount {}", account);
        accountRepository.save(account);
    }

    @Override
    public String login(Account account) throws UserNotFoundException {
        log.info("Login with email {}", account.getEmail());
        Account existedAccount = accountRepository.findByEmailAndPassword(account.getEmail(), account.getPassword());
        if (existedAccount == null || existedAccount.getEmail() == null || existedAccount.getNickName() == null) {
              throw new UserNotFoundException();
        }
        return tokenGenerator.createJWT(existedAccount.getNickName(), existedAccount.getEmail(), TOKEN_EXPIRATION_MMS);
    }
}
