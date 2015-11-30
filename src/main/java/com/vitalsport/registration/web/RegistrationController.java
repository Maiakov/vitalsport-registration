package com.vitalsport.registration.web;

import com.vitalsport.registration.exeption.UserNotFoundException;
import com.vitalsport.registration.model.Account;
import com.vitalsport.registration.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/registration")
public class RegistrationController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public ResponseEntity<String> checkLogin(@RequestBody Account payload) {
        if (accountService.isLoginFree(payload.getEmail())) {
            return ResponseEntity.ok("Login is free");
        }
        return ResponseEntity.badRequest().body("Login is busy");
    }

    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public ResponseEntity<String> saveAccount(@RequestBody Account payload) {
        if (!accountService.isLoginFree(payload.getEmail())) {
            return ResponseEntity.badRequest().body("Account not saved - login is busy");
        }
        accountService.saveAccount(payload);
        return ResponseEntity.ok("Account saved");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody Account payload, HttpServletResponse response) {
        String token = null;
        try {
            token = accountService.login(payload);
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        response.setHeader("token", token);
        return ResponseEntity.ok().body(payload.getNickName());
    }
}


