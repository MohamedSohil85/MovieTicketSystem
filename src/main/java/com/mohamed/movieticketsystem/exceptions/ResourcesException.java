package com.mohamed.movieticketsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class ResourcesException extends Exception {
    public ResourcesException(String message) {
        super(message);
    }
}
