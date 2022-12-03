package com.panupat.baseproject.exception;

public class AuthenticationFailException extends BaseException {
    public AuthenticationFailException() {
        super("1001", "Username or password incorrect");
    }
}
