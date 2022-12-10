package com.university.course.schoolmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadUserVerificationExceptionController {
    @ExceptionHandler(value = BadUserVerificationException.class)
    public ResponseEntity<Object> exception(BadUserVerificationException exception) {
        return new ResponseEntity<>("User has not been successfully verified since your token is invalid", HttpStatus.BAD_REQUEST);
    }

}
