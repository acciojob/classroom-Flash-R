package com.driver.customExceptions;

public class TeacherAlreadyExists extends RuntimeException{
    public TeacherAlreadyExists(String message){
        super(message);
    }
}
