package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.domain.exception.ForbiddenUserException;
import com.pragma.powerup.infrastructure.exception.ForbiddenOwnerException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(ForbiddenUserException.class)
    public ResponseEntity<Map<String, String>> handleForbiddenUserException(
            ForbiddenUserException ignoredForbiddenUserException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.FORBIDDEN_USER.getMessage()));
    }

    @ExceptionHandler(ForbiddenOwnerException.class)
    public ResponseEntity<Map<String, String>> handleForbiddenOwnerException(
            ForbiddenOwnerException ignoredForbiddenUserException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.FORBIDDEN_OWNER.getMessage()));
    }
    
}