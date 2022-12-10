package com.university.course.schoolmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmailNotFoundExceptionHandler extends Throwable {
    @ExceptionHandler(value = EmailNotFoundException.class)
    public ResponseEntity<Object> exception(EmailNotFoundException exception) {
        return new ResponseEntity<>("User's email does not exist", HttpStatus.BAD_REQUEST);
    }
}
