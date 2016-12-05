package com.nc.task3.jsp_controller;


public class Result {

    private boolean result;
    private String message;

    public Result(boolean result) {
        this.result = result;
    }

    public Result(boolean result, String message) {
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
