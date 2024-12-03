package com.backend.atatest.exception;

import com.backend.atatest.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setStatusCode(ex.getHttpStatus().value());
        response.setMessage(ex.getHttpStatus().getReasonPhrase());
        response.setDescription(ex.getDesc());
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }
}