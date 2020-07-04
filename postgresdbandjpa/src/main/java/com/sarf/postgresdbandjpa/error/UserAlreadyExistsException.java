package com.sarf.postgresdbandjpa.error;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("User already exists with username : " + username);
    }
}
