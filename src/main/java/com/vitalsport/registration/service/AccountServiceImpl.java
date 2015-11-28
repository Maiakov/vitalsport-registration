package com.vitalsport.registration.service;

import com.vitalsport.registration.model.Account;
import com.vitalsport.registration.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean isLoginFree(String email) {
        log.info("Searching account with login {}", email);
        Account existedAccount = accountRepository.findOne(email);
        return existedAccount == null;
    }

    @Override
    public void saveAccount(Account account) {
        log.info("Saving aacount {}", account);
        accountRepository.save(account);
    }
}
