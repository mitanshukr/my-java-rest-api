package com.example.myRestApi.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorBody {
    private String message;
    private HttpStatus code;
    private LocalDateTime timestamp;
    public ErrorBody(String message, HttpStatus code, LocalDateTime timestamp) {
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
