package com.challenge.restaurant.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by sachin on 4/7/19.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorRespose {

    String message;

    String details;

    public ErrorRespose(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
