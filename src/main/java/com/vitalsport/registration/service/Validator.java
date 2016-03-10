package com.vitalsport.registration.service;


import com.vitalsport.registration.exception.IncorrectEmailException;
import com.vitalsport.registration.exception.IncorrectNickNameException;

public class Validator {

    public static void validateEmail(String email) throws IncorrectEmailException {
        if (email == null) {
            throw new IncorrectEmailException();
        }
        boolean matches = email.matches("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*" +
                "@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*" +
                "(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$");
        if (!matches) {
            throw new IncorrectEmailException();
        }
    }

    public static void validateNickName(String nickName) throws IncorrectNickNameException {
        if (nickName == null || nickName.length() < 3) {
            throw new IncorrectNickNameException();
        }

        boolean matches = nickName.matches("^[a-zA-Z0-9]+$");
        if (!matches) {
            throw new IncorrectNickNameException();
        }

    }
}
