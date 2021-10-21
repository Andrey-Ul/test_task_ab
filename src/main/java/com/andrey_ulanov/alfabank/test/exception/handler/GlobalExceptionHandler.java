package com.andrey_ulanov.alfabank.test.exception.handler;

import com.andrey_ulanov.alfabank.test.exception.NotCorrectCurrencyCode;
import com.andrey_ulanov.alfabank.test.exception.NotExistCurrencyCode;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.andrey_ulanov.alfabank.test.exception.response.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
        super();
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Response> FeignException(Exception e) {
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }
    @ExceptionHandler(NotCorrectCurrencyCode.class)
    public ResponseEntity<Response> CodeCurrencyException(Exception e) {
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotExistCurrencyCode.class)
    public ResponseEntity<Response> ExistCurrencyException(Exception e) {
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> OtherException(Exception e) {
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
