package de.neuefische.springexceptionhandlingtask;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchElementException(NoSuchElementException exception,
                                                                     WebRequest webRequest){
        ErrorMessage errorMsg = new ErrorMessage(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleAnyException(Exception exception,
                                                           WebRequest webRequest){
        ErrorMessage errorMsg = new ErrorMessage(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "User f**k up: " + exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
