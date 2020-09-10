package com.challenge.restaurant.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.restaurant.exception.IllegalOrderException;
import com.challenge.restaurant.model.Order;
import com.challenge.restaurant.model.OrderStatus;
import com.challenge.restaurant.repository.OrderRepository;
import com.challenge.restaurant.utils.OrderValidationUtils;

/**
 * Created by sachin on 4/7/19.
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order createOrder(Order order) {

		if (order == null || order.getItemName() == null)
			throw new IllegalOrderException("Invalid order!!");
		order.setOrderPlacedTime(LocalDateTime.now());
		order.setStatus(OrderStatus.PLACED.getText().toUpperCase());
		
		return orderRepository.save(order);
	}

	@Override
	public Order searchOrder(long id) {
		Order order = orderRepository.findOrderById(id);
		if (order == null) {
			throw new IllegalOrderException(OrderValidationUtils.ORDER_NOT_FOUND_MSG + id);
		}
		return order;
	}

	@Override
	public void deleteOrder(long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public Order updateStatus(long id, OrderStatus status) {

		Order orderFound = searchOrder(id);
		orderFound.setStatus(status.getText());

		orderRepository.save(orderFound);

		return orderFound;
	}
}
