package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.dao.DataAccessResourceFailureException;
import com.mongodb.MongoTimeoutException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {DataAccessResourceFailureException.class, MongoTimeoutException.class})
    public ResponseEntity<String> handleDatabaseConnectionExceptions(Exception ex) {
        String message = "MongoDB Connection Failed: Please ensure your current IP address is whitelisted in MongoDB Atlas Network Access pane. (Error: " + ex.getMessage() + ")";
        return new ResponseEntity<>(message, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
