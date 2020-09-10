package com.challenge.restaurant.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sachin on 4/7/19.
 */
@Entity
@Table(name = "delivery_person")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryPerson {
    @Id
    @GeneratedValue
    Long id;

    @Column
    String name;

    @Column
    Boolean active;

    @OneToOne
    Order order;

    DeliveryPerson(){}

    public DeliveryPerson(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
