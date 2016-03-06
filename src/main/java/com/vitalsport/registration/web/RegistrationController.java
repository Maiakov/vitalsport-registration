package com.vitalsport.registration.web;

import com.vitalsport.registration.exception.IncorrectNickNameException;
import com.vitalsport.registration.exception.UserNotFoundException;
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
        return returnError("Login is busy");
    }

    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public ResponseEntity<Status> saveAccount(@RequestBody Account payload, HttpServletResponse response) {
        if (!accountService.isLoginFree(payload.getEmail())) {
            return returnError("Account not saved - login is busy");
        }
        try {
            String token = accountService.saveAccount(payload);
            addTokenToResponse(response, token);
            Status status = Status.SUCCESS;
            status.setMessage("Account saved");
            return ResponseEntity.ok(status);
        } catch (IncorrectNickNameException | UserNotFoundException e) {
            return returnError("User not found");
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Status> login(@RequestBody Account payload, HttpServletResponse response) {
        try {
            Status status = Status.SUCCESS;
            addTokenToResponse(response, accountService.login(payload));
            status.setMessage(payload.getNickName());
            return ResponseEntity.ok().body(status);
        } catch (IncorrectNickNameException | UserNotFoundException e) {
            return returnError("User not found");
        }
    }

    private void addTokenToResponse(HttpServletResponse response, String token) {
        response.setHeader("token", token);
        response.setHeader("Access-Control-Expose-Headers", "token");
    }

    private ResponseEntity<Status> returnError(String message) {
        Status status = Status.ERROR;
        status.setMessage(message);
        return ResponseEntity.badRequest().body(status);
    }
}


