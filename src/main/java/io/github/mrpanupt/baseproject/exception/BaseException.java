package io.github.mrpanupt.baseproject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
}
