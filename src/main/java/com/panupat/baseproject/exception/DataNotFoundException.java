package com.panupat.baseproject.exception;

public class DataNotFoundException extends BaseException {
    public DataNotFoundException(String message) {
        setErrorCode("3001");
        setErrorMessage(message);
    }
}
