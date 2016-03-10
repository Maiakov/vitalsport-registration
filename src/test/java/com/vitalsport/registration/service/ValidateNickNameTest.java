package com.vitalsport.registration.service;

import com.vitalsport.registration.exception.IncorrectNickNameException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidateNickNameTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testValidateNickName_empty() throws Exception {
        exception.expect(IncorrectNickNameException.class);
        Validator. validateNickName("");
    }

    @Test
    public void testValidateNickName_WithBannedSymbols() throws Exception {
        exception.expect(IncorrectNickNameException.class);
        Validator. validateNickName("na#me");
    }

    @Test
    public void testValidateNickName_null() throws Exception {
        exception.expect(IncorrectNickNameException.class);
        Validator. validateNickName(null);
    }
    @Test
    public void testValidateNickName_dog() throws Exception {
        exception.expect(IncorrectNickNameException.class);
        Validator. validateNickName("123@");
    }

    @Test
    public void testValidateNickName_valid() throws Exception {
        Validator.validateNickName("serg");
    }

}