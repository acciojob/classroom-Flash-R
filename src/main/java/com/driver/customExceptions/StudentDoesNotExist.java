package com.driver.customExceptions;

public class StudentDoesNotExist extends RuntimeException{
    public StudentDoesNotExist(String message) {
        super(message);
    }
}
