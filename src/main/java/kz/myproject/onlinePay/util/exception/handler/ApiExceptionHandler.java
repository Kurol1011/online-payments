package kz.myproject.onlinePay.util.exception.handler;

import io.jsonwebtoken.ExpiredJwtException;
import kz.myproject.onlinePay.util.exception.BankAccountTransactionException;
import kz.myproject.onlinePay.util.exception.CustomExceptionResponse;
import kz.myproject.onlinePay.util.exception.UserIsAlreadyExistException;
import kz.myproject.onlinePay.util.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
