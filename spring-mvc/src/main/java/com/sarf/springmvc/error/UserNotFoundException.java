package com.sarf.springmvc.error;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id, String operation) {
        super("User not found with id : " + id + " for " + operation.toUpperCase());
    }
}

