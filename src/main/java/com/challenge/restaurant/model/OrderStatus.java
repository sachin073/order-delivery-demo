package com.challenge.restaurant.model;

/**
 * Created by sachin on 4/7/19.
 */

public enum OrderStatus {
    PLACED("placed"),BAKING("baking"),READY_TO_DELIVER("ready to deliver"),
    PICKED("picked"),ENROUNTE("en-route"),DELEVERED("delivered");
    private final String text;

    OrderStatus(final String text){
        this.text=text;
    }


    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
