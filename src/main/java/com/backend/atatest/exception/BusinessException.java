package com.backend.atatest.exception;

import com.backend.atatest.constant.ResponseMessageEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends Throwable {
    private final HttpStatus httpStatus;
    private final String desc;

    public BusinessException(HttpStatus httpStatus, String desc) {
        this.httpStatus = httpStatus;
        this.desc = desc;
    }
    public BusinessException(ResponseMessageEnum responseMessageEnum, String errorValue) {
        this.httpStatus = responseMessageEnum.getHttpStatus();
        this.desc = String.format(responseMessageEnum.getMessage(), errorValue);
    }
}
