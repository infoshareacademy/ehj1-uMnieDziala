package com.isa.unasdziala.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OutOfFreeDaysException extends RuntimeException {
    public OutOfFreeDaysException() {
        super("You dont have free days!");
    }
}
