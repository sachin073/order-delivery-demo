package com.challenge.restaurant.service;

import com.challenge.restaurant.model.Order;
import com.challenge.restaurant.model.OrderStatus;

public interface OrderService {

	Order createOrder(Order order);

	Order searchOrder(long id);

	Order updateStatus(long id, OrderStatus status);

	void deleteOrder(long id);

}