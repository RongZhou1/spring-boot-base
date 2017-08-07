package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotExitException extends RuntimeException {
    public ResourceNotExitException(String message) {
        super(message);
    }
}
