package com.challenge.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.challenge.restaurant.model.ErrorRespose;

/**
 * Created by sachin on 5/7/19.
 */
@ControllerAdvice
public class GenericExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRespose> genericHandler(Exception e){
        return new ResponseEntity<ErrorRespose>(new ErrorRespose(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalOrderException.class)
    public ResponseEntity<ErrorRespose> customOrderExceptionHandler(Exception e){
        return new ResponseEntity<ErrorRespose>(new ErrorRespose(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
