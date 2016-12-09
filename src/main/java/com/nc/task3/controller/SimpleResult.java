package com.nc.task3.controller;


public class SimpleResult {

    private boolean result;
    private String message;

    public SimpleResult(boolean result) {
        this.result = result;
    }

    public SimpleResult(boolean result, String message) {
        this(result);
        this.message = message;
    }

    public boolean getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
