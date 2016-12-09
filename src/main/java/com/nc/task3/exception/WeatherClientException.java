package com.nc.task3.exception;


public class WeatherClientException extends Exception {

    public static final String PARSE_MESSAGE = "Error in parsing value from xml";
    public static final String READ_MESSAGE = "Error in reading value from web-service";


    public WeatherClientException(String message) {
        super(message);
    }

}
