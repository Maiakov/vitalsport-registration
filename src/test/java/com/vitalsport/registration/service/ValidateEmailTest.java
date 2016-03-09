package com.vitalsport.registration.service;

import com.vitalsport.registration.exception.IncorrectEmailException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidateEmailTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testValidateEmail_empty() throws Exception {
        exception.expect(IncorrectEmailException.class);
        Validator.validateEmail("");
    }

    @Test
    public void testValidateEmail_null() throws Exception {
        exception.expect(IncorrectEmailException.class);
        Validator.validateEmail(null);
    }

    @Test
    public void testValidateEmail_dog() throws Exception {
        Validator.validateEmail("rasulov@gmail.com");
    }

    @Test
    public void testValidateEmail_WithoutDog() throws Exception {
        exception.expect(IncorrectEmailException.class);
        Validator.validateEmail("rasulov.gmail.com");
    }

    @Test
    public void testValidateEmail_nothingAfterDog() throws Exception {
        exception.expect(IncorrectEmailException.class);
        Validator.validateEmail("rasulov@");
    }

    @Test
    public void testValidateEmail_nothingBeforeDog() throws Exception {
        exception.expect(IncorrectEmailException.class);
        Validator.validateEmail("@gmail.com");
    }
    @Test
    public void testValidateEmail_WithoutDomain() throws Exception {
        exception.expect(IncorrectEmailException.class);
        Validator.validateEmail("m@m");
    }
}