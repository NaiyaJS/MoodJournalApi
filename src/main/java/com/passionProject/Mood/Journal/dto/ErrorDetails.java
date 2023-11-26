package com.passionProject.Mood.Journal.dto;

public class ErrorDetails {
    private int StatusCode;
    private String message;

    public ErrorDetails() {
    }

    public ErrorDetails(int statusCode, String message) {
        StatusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
