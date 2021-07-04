package com.challenge.restaurant.controller;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.restaurant.model.DeliveryPerson;
import com.challenge.restaurant.model.ErrorRespose;
import com.challenge.restaurant.model.Order;
import com.challenge.restaurant.model.OrderStatus;
import com.challenge.restaurant.request.model.OrderRequest;
import com.challenge.restaurant.response.mapper.OrderMapperService;
import com.challenge.restaurant.response.model.OrderDetailsResponse;
import com.challenge.restaurant.service.OrderService;
import com.challenge.restaurant.service.PersonService;
import com.challenge.restaurant.utils.OrderValidationUtils;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Created by sachin on 4/7/19.
 */
@RestController
@RequestMapping("/restaurant")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	PersonService deliveryPersonService;

	@Autowired
	MeterRegistry registry;

	@Autowired
	OrderMapperService mapperService;

	Gauge counter = null;
	static int placeApiHits = 0;

	@PostConstruct
	public void init() {
		counter = Gauge.builder("poll_counter", new Supplier<Number>() {

			@Override
			public Number get() {
				return placeApiHits;
			}
		}).register(registry);
	}

	@PostMapping(path = "/order")
	public ResponseEntity<OrderDetailsResponse> placeorder(@RequestBody OrderRequest order) {
		placeApiHits++;
		Order placedOrder = new Order(order.getItemName(), order.getCost());
		orderService.createOrder(placedOrder);

		return new ResponseEntity<>(mapperService.mapOrderDetails(placedOrder), HttpStatus.OK);
	}

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderDetailsResponse> getStatus(@PathVariable("id") String id) {
		long oId = OrderValidationUtils.parseOrderId(id);

		Order placedOrder = orderService.searchOrder(oId);

		if (placedOrder == null) {
			new ResponseEntity<ErrorRespose>(new ErrorRespose(OrderValidationUtils.ORDER_NOT_FOUND_MSG + id),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(mapperService.mapOrderDetails(placedOrder), HttpStatus.OK);
	}

	@DeleteMapping(value = "/orders/{id}")
	public ResponseEntity<OrderDetailsResponse> deleteOrder(@PathVariable("id") String id) {

		long oId = OrderValidationUtils.parseOrderId(id);

		Order placedOrder = orderService.searchOrder(oId);
		if (placedOrder == null) {
			new ResponseEntity<ErrorRespose>(new ErrorRespose(OrderValidationUtils.ORDER_NOT_FOUND_MSG + id),
					HttpStatus.NOT_FOUND);
		}
		orderService.deleteOrder(oId);

		return new ResponseEntity<>(mapperService.mapOrderDetails(placedOrder), HttpStatus.OK);
	}

	@PutMapping(path = "/orders/{id}/status/{status}")
	public ResponseEntity<OrderDetailsResponse> updateStatus(@PathVariable String id,
			@PathVariable OrderStatus status) {

		long oId = OrderValidationUtils.parseOrderId(id);

		Order placedOrder = orderService.updateStatus(oId, status);

		return new ResponseEntity<>(mapperService.mapOrderDetails(placedOrder), HttpStatus.OK);
	}

	@GetMapping(path = "/orders/getDeliveryReport")
	public ResponseEntity<List<DeliveryPerson>> getDeliveryReport() {
		List<DeliveryPerson> personList = null;
		personList = deliveryPersonService.getAllActive();

		return new ResponseEntity<>(personList, HttpStatus.OK);
	}

}
