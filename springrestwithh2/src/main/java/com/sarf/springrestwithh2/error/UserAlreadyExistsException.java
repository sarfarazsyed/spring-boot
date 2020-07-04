package com.sarf.springrestwithh2.error;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("User already exists with username : " + username);
    }
}
