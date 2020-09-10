package com.challenge.restaurant.exception;

/**
 * Created by sachin on 4/7/19.
 */
public class IllegalOrderException extends RuntimeException {

    public IllegalOrderException(String msg){
        super(msg);
    }
}
