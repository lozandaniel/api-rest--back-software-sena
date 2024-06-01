package com.example.backproyectswdistriquesos.exception;

import com.example.backproyectswdistriquesos.models.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdviceErrors {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ResponseMessage<String>> runtimeExceptionBadRequest(RuntimeException ex) {
        ResponseMessage<String> error =
                new ResponseMessage<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidCredentialsException.class)
    public ResponseEntity<ResponseMessage<String>> handleInvalidCredentials(InvalidCredentialsException ex) {
        ResponseMessage<String> errorResponse =
                new ResponseMessage<>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
