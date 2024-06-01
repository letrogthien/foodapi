package com.foodapi.demo.exceptions;

public class NoSuchElementException extends RuntimeException {
    public NoSuchElementException(String message){
        super(message);
    }
}
