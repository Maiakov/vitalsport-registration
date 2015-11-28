package com.vitalsport.registration.web;

import com.vitalsport.registration.model.Account;
import com.vitalsport.registration.service.AccountService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {
    @Autowired
    private AccountService account;

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public ResponseEntity<String> checkLogin(@RequestBody Account payload) {
        if (account.isLoginFree(payload.getEmail())) {
            return ResponseEntity.ok("Login is free");
        }
        return ResponseEntity.badRequest().body("Login is busy");
    }

    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public ResponseEntity<String> saveAccount(@RequestBody Account payload) {
        if (!account.isLoginFree(payload.getEmail())) {
            return ResponseEntity.badRequest().body("Account not saved - login is busy");
        }
        account.saveAccount(payload);
        return ResponseEntity.ok("Account saved");
    }

}


