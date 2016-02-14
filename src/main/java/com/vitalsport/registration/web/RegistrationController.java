package com.vitalsport.registration.web;

import com.vitalsport.registration.exeption.UserNotFoundException;
import com.vitalsport.registration.model.Account;
import com.vitalsport.registration.model.Status;
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
    public ResponseEntity<Status> checkLogin(@RequestBody Account payload) {
        if (accountService.isLoginFree(payload.getEmail())) {
            Status success = Status.SUCCESS;
            success.setMessage("Login is free");
            return ResponseEntity.ok(success);
        }
        Status error = Status.ERROR;
        error.setMessage("Login is busy");
        return ResponseEntity.badRequest().body(error);
    }

    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public ResponseEntity<Status> saveAccount(@RequestBody Account payload) {
        if (!accountService.isLoginFree(payload.getEmail())) {
            Status error = Status.ERROR;
            error.setMessage("Account not saved - login is busy");
            return ResponseEntity.badRequest().body(error);
        }
        accountService.saveAccount(payload);
        Status error = Status.SUCCESS;
        error.setMessage("Account saved");
        return ResponseEntity.ok(Status.SUCCESS);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Status> login(@RequestBody Account payload, HttpServletResponse response) {
        String token = null;
        try {
            token = accountService.login(payload);
        } catch (UserNotFoundException e) {
            Status error = Status.ERROR;
            error.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
        response.setHeader("token", token);
        response.setHeader("Access-Control-Expose-Headers", "token");
        Status error = Status.SUCCESS;
        error.setMessage(payload.getNickName());
        return ResponseEntity.ok().body(error);
    }
}


