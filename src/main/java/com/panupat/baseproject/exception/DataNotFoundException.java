package com.panupat.baseproject.exception;

public class DataNotFoundException extends BaseException {
    public DataNotFoundException(String message) {
        super("3001", message);
    }
}
