package com.panupat.baseproject.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException{
    private String errorCode;
    private String errorMessage;
}
