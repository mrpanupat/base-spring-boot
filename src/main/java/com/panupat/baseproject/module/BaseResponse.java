package com.panupat.baseproject.module;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseResponse<T> {

    private Status status;
    private T data;

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setStatus(new Status("0", "success"));
        response.setData(data);
        return response;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Status {
        private String code;
        private String message;
    }
}
