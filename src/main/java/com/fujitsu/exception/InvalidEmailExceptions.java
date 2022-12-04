package com.fujitsu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidEmailExceptions extends Exception{
    public InvalidEmailExceptions(String message) {
        super(message);
    }
}
