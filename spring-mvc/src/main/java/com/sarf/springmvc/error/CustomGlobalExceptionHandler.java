package com.sarf.springmvc.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> springHandleNotFound(UserNotFoundException u, WebRequest response) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(u.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> springHandleDuplicate(UserNotFoundException u, WebRequest response) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(u.getMessage());
    }

    @ExceptionHandler(UnSupportedFieldPatchException.class)
    public ResponseEntity<String> springUnSupportedFieldPatch(UserNotFoundException u, WebRequest response) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(u.getMessage());
    }

}