package com.vitalsport.registration.service;

import com.vitalsport.registration.exception.IncorrectNickNameException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountServiceImplTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private AccountServiceImpl service =  new AccountServiceImpl();

    @Test
    public void testValidateNickName_empty() throws Exception {
        exception.expect(IncorrectNickNameException.class);
        service. validateNickName("");
    }
    @Test
    public void testValidateNickName_null() throws Exception {
        exception.expect(IncorrectNickNameException.class);
        service. validateNickName(null);
    }
    @Test
    public void testValidateNickName_dog() throws Exception {
        exception.expect(IncorrectNickNameException.class);
        service. validateNickName("123@");
    }

    @Test
    public void testValidateNickName_valid() throws Exception {
        service.validateNickName("serg");
    }

}