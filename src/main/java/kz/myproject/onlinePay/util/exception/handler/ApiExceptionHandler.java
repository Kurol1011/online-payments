package kz.myproject.onlinePay.util.exception.handler;

import io.jsonwebtoken.ExpiredJwtException;
import kz.myproject.onlinePay.util.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<CustomExceptionResponse> handleException(UserNotFoundException e){
        CustomExceptionResponse response = new CustomExceptionResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserIsAlreadyExistException.class,ExpiredJwtException.class, BankAccountTransactionException.class})
    public ResponseEntity<CustomExceptionResponse> handleException(RuntimeException e){
        CustomExceptionResponse response = new CustomExceptionResponse(e.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RegisterRequestDataException.class})
    public ResponseEntity<CustomExceptionResponse> handleException(RegisterRequestDataException e){
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage()).append("\n");
        }
        CustomExceptionResponse response = new CustomExceptionResponse(errorMessage.toString(),LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
