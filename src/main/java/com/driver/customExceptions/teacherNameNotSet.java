package com.driver.customExceptions;

public class teacherNameNotSet extends RuntimeException{
    public teacherNameNotSet(String message){
        super(message);
    }
}
