package com.challenge.restaurant.controller;

import com.challenge.restaurant.model.DeliveryPerson;
import com.challenge.restaurant.model.Order;
import com.challenge.restaurant.service.DeliveryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/delivery")
public class DeliveryPersonController {

	@Autowired
	DeliveryService deliveryPersonService;

	@PostMapping("/assignOrders/persons/{pId}/orders/{oId}")
	public ResponseEntity<String> assignOrder(@PathVariable("pId") Long personId, @PathVariable("oId") Long orderId) {

		DeliveryPerson deliveryPerson = null;

		deliveryPerson = deliveryPersonService.fetchAndAssignOrder(personId, orderId);

		return new ResponseEntity<String>(deliveryPerson.getOrder().getStatus(), HttpStatus.OK);
	}

	@GetMapping("/status/{pId}")
	public ResponseEntity<Map<String, Object>> assignOrder(@PathVariable("pId") Long personId) {
		Map<String, Object> deliveryDetails = null;

		deliveryDetails = deliveryPersonService.getDeliveryStatus(personId);

		return new ResponseEntity<>(deliveryDetails, HttpStatus.OK);
	}

}
