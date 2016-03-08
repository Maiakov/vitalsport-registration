package com.vitalsport.registration.service;

import com.vitalsport.registration.exception.IncorrectEmailException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidateEmailTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private AccountServiceImpl service = new AccountServiceImpl();

    @Test
    public void testValidateEmail_empty() throws Exception {
        exception.expect(IncorrectEmailException.class);
        service.validateEmail("");
    }

    @Test
    public void testValidateEmail_null() throws Exception {
        exception.expect(IncorrectEmailException.class);
        service.validateEmail(null);
    }

    @Test
    public void testValidateEmail_dog() throws Exception {
        service.validateEmail("rasulov@gmail.com");
    }

    @Test
    public void testValidateEmail_WithoutDog() throws Exception {
        exception.expect(IncorrectEmailException.class);
        service.validateEmail("rasulov.gmail.com");
    }

    @Test
    public void testValidateEmail_nothingAfterDog() throws Exception {
        exception.expect(IncorrectEmailException.class);
        service.validateEmail("rasulov@");
    }

    @Test
    public void testValidateEmail_nothingBeforeDog() throws Exception {
        exception.expect(IncorrectEmailException.class);
        service.validateEmail("@gmail.com");
    }
    @Test
    public void testValidateEmail_WithoutDomain() throws Exception {
        exception.expect(IncorrectEmailException.class);
        service.validateEmail("m@m");
    }
}