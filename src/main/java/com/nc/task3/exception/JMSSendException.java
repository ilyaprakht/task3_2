package com.nc.task3.exception;


public class JMSSendException extends Exception {

    public static final String SEND_JMS_IN_QUEUE_MESSAGE = "Error in sending message to queue";

    public JMSSendException(String message) {
        super(message);
    }

}