package com.nc.task3.exception;


public class JMSListenException extends Exception {

    public static final String DAO_MESSAGE = "Error in saving data in database";
    public static final String UNKNOWN_MESSAGE = "Unknown error in listening message from jms";

    public JMSListenException(String message) {
        super(message);
    }

}