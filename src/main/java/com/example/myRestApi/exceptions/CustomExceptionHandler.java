package com.example.myRestApi.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<ErrorBody> handleAllExceptions(Exception ex, HttpServletResponse response) {
        ErrorBody err = new ErrorBody(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<ErrorBody>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public final ResponseEntity<ErrorBody> handleUserNotFoundExceptions(Exception ex, HttpServletResponse response) {
        ErrorBody err = new ErrorBody(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errMessages = ex.getFieldErrors().stream().map(field -> field.getDefaultMessage()).toList();
        ErrorBody err = new ErrorBody(errMessages.toString(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

        return new ResponseEntity(err, HttpStatus.BAD_REQUEST);
    }
}
