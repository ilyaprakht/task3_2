package com.nc.task3.exception;


public class DAOException extends Exception {

    public static final String UPDATE_MESSAGE = "Error in updating entity in database";
    public static final String INSERT_MESSAGE = "Error in inserting entity in database";
    public static final String SELECT_MESSAGE = "Error in selecting entity from database";

    public DAOException(String message) {
        super(message);
    }

}