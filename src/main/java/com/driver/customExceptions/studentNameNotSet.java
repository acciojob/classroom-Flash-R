package com.driver.customExceptions;

public class studentNameNotSet extends RuntimeException{
    public studentNameNotSet(String message){
        super(message);
    }
}
