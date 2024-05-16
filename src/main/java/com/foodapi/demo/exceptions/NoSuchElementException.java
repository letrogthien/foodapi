package com.foodapi.demo.exceptions;

public class NoSuchElementException extends RuntimeException {
    public NoSuchElementException( ){
        super("Element No Such");
    }
}
