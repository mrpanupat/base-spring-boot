package com.panupat.baseproject.exception;

public class AuthenticationFailException extends BaseException {
    public AuthenticationFailException() {
        setErrorCode("1001");
        setErrorMessage("Username or password incorrect");
    }
}
