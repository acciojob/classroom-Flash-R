package com.driver.customExceptions;

public class TeacherDoesNotExist extends RuntimeException{
    public TeacherDoesNotExist(String message) {
        super(message);
    }
}
