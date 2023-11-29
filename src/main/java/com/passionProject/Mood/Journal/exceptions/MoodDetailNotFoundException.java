package com.passionProject.Mood.Journal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MoodDetailNotFoundException extends RuntimeException{
    public MoodDetailNotFoundException(String message) {
        super(message);
    }
}
