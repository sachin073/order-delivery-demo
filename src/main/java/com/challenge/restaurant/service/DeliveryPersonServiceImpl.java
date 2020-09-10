package com.challenge.restaurant.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.restaurant.exception.IllegalOrderException;
import com.challenge.restaurant.model.DeliveryPerson;
import com.challenge.restaurant.model.Order;
import com.challenge.restaurant.model.OrderStatus;
import com.challenge.restaurant.repository.DeliveryPersonRepository;
import com.challenge.restaurant.repository.OrderRepository;

@Service
public class DeliveryPersonServiceImpl implements PersonService, DeliveryService {

	@Autowired
	DeliveryPersonRepository deliveryPersonRepository;

	@Autowired
	OrderRepository orderRepository;

	@Override
	public List<DeliveryPerson> getAllActive() {

		return deliveryPersonRepository.findAllByActive(true);
	}

	@Override
	public DeliveryPerson fetchAndAssignOrder(Long personId, Long orderId) {
		Order order = orderRepository.findOrderById(orderId);
		DeliveryPerson deliveryPerson = deliveryPersonRepository.findDeliveryPersonById(personId);

		if (order == null || deliveryPerson == null) {
			throw new IllegalOrderException("order assignment fail. Invalid person or order");
		}
		return assignOrderToPerson(deliveryPerson, order);
	}

	private DeliveryPerson assignOrderToPerson(DeliveryPerson person, Order order) {
		person.setActive(true);
		order.setStatus(OrderStatus.READY_TO_DELIVER.getText());
		person.setOrder(order);
		deliveryPersonRepository.save(person);
		return person;
	}

	@Override
	public Map<String, Object> getDeliveryStatus(Long personId) {
		DeliveryPerson person = deliveryPersonRepository.findDeliveryPersonById(personId);
		Map<String, Object> deliveryDetailsMap = new HashMap<>();
		if (person == null) {
			throw new IllegalOrderException("unknown person");
		}

		deliveryDetailsMap.put("Delivery person name", person.getName());
		if (person.getActive()) {
			deliveryDetailsMap.put("Scheduled for delivery", "Yes");
			deliveryDetailsMap.put("Order Details", person.getOrder());
		} else {
			deliveryDetailsMap.put("Scheduled for delivery", "Yes");
		}

		deliveryDetailsMap.put("Delivery person name", person.getName());

		return deliveryDetailsMap;
	}

}
