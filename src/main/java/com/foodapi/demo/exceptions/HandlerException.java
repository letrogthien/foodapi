package com.foodapi.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.foodapi.demo.exceptions.User.UserAlreadyExist;
import com.foodapi.demo.exceptions.jwtEx.TokenNotFound;


@ControllerAdvice
public class HandlerException {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(System.currentTimeMillis()), "Thông tin đăng nhập không chính xác!");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(System.currentTimeMillis()), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<ErrorResponse> handlerUserAlreadyExeption(UserAlreadyExist ex){
        ErrorResponse errorResponse=new ErrorResponse(new Date(System.currentTimeMillis()),ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    
    @ExceptionHandler(TokenNotFound.class)
    public ResponseEntity<ErrorResponse> handlerTokenNotFound(TokenNotFound ex){
        ErrorResponse errorResponse=new ErrorResponse(new Date(System.currentTimeMillis()),ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse(new Date(System.currentTimeMillis()),ex.getMessage());
      return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> noSuchElement(Exception ex) {
        ErrorResponse error = new ErrorResponse(new Date(System.currentTimeMillis()),ex.getMessage());
      return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
