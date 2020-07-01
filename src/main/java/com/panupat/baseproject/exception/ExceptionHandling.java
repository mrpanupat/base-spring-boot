package com.panupat.baseproject.exception;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panupat.baseproject.module.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionHandling {

    private final ObjectMapper mapObject = new ObjectMapper();

    @ExceptionHandler({BaseException.class})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public BaseResponse<?> baseExceptionHandling(BaseException exception) {
        log.error("===== Start: baseExceptionHandling =====");
        BaseResponse<?> baseResponse = new BaseResponse<>();
        BaseResponse.Status status = new BaseResponse.Status(
                exception.getErrorCode(),
                exception.getErrorMessage());
        baseResponse.setStatus(status);
        log.error("===== End: baseExceptionHandling =====");
        return baseResponse;
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                log.error("===== Start: spring default exception handling =====");
                Map<String, Object> defaultMap = super.getErrorAttributes(webRequest, options);
                BaseResponse<?> baseResponse = new BaseResponse<>();
                BaseResponse.Status status = new BaseResponse.Status(
                        defaultMap.get("status").toString(),
                        defaultMap.get("error").toString());
                baseResponse.setStatus(status);
                Map<String, Object> map = mapObject.convertValue(baseResponse, new TypeReference<>() {
                });
                log.error("===== End: spring default exception handling =====");
                return map;
            }
        };
    }
}
