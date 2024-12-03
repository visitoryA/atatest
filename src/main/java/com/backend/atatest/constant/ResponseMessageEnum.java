package com.backend.atatest.constant;

import org.springframework.http.HttpStatus;

public enum ResponseMessageEnum {
    SUCCESS(HttpStatus.OK, "Success"),
    INVALID_REQ(HttpStatus.BAD_REQUEST, "%s is invalid"),
    MISSING_REQ(HttpStatus.BAD_REQUEST, "%s is missing");

    private final HttpStatus httpStatus;
    private final String message;

    ResponseMessageEnum(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String getMessage() {
        return this.message;
    }
}
