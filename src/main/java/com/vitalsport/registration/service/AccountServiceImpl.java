package com.vitalsport.registration.service;

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
    public boolean isLoginFree(String email) {
        log.info("Searching account with login {}", email);
        Account existedAccount = accountRepository.findByEmail(email);
        return existedAccount == null;
    }

    @Override
    public String saveAccount(Account account) throws UserNotFoundException, IncorrectNickNameException {
        log.info("Saving account {}", account);
        accountRepository.save(account);
        return login(account);
    }

    @Override
    public String login(Account account) throws UserNotFoundException, IncorrectNickNameException {
        log.info("Login with email {}", account.getEmail());
        validateNickName(account.getNickName());
        Account existedAccount = accountRepository.findByEmailAndPasswordOrNickNameAndPassword(account.getEmail(), account.getPassword());
        if (existedAccount == null || existedAccount.getEmail() == null || existedAccount.getNickName() == null) {
            throw new UserNotFoundException();
        }
        return tokenGenerator.createJWT(existedAccount.getNickName(), String.valueOf(existedAccount.getId()), TOKEN_EXPIRATION_MMS);
    }

    void validateNickName(String nickName) throws IncorrectNickNameException {
        if (nickName == null || nickName.length() < 3) {
            throw new IncorrectNickNameException();
        }

        boolean matches = nickName.matches("^[a-zA-Z0-9_\\.]*$");
        if (!matches) {
            throw new IncorrectNickNameException();
        }

    }
}
