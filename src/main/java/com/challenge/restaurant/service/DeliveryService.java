package com.challenge.restaurant.service;

import java.util.Map;

import com.challenge.restaurant.model.DeliveryPerson;

public interface DeliveryService {

	DeliveryPerson fetchAndAssignOrder(Long personId, Long orderId);

	Map<String, Object> getDeliveryStatus(Long personId);

}