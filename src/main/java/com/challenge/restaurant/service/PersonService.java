package com.challenge.restaurant.service;

import java.util.List;

import com.challenge.restaurant.model.DeliveryPerson;

public interface PersonService {

	List<DeliveryPerson> getAllActive();

}