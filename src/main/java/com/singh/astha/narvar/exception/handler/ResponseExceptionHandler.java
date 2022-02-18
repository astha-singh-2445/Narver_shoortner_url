package com.singh.astha.narvar.exception.handler;

import com.singh.astha.narvar.dtos.response.ResponseWrapper;
import com.singh.astha.narvar.exception.ResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ResponseWrapper<Object>> handle(HttpServletRequest httpServletRequest,
                                                          ResponseException exception) {
        ResponseWrapper<Object> responseWrapper = ResponseWrapper.failure(exception.getPayload(),
                exception.getMessage());
        return new ResponseEntity<>(responseWrapper, exception.getStatus());
    }
}
