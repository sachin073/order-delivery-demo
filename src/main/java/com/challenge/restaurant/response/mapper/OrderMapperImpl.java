package com.challenge.restaurant.response.mapper;

import org.springframework.stereotype.Service;

import com.challenge.restaurant.model.Order;
import com.challenge.restaurant.response.model.OrderDetailsResponse;

@Service
public class OrderMapperImpl implements OrderMapperService {

	@Override
	public OrderDetailsResponse mapOrderDetails(Order order) {
		OrderDetailsResponse orderResponse = new OrderDetailsResponse();
		
		orderResponse.setCost(order.getCost());	
		orderResponse.setId("DEL+"+order.getId().toString());
		orderResponse.setItemName(order.getItemName());
		orderResponse.setStatus(order.getStatus());
		orderResponse.setOrderPlacedTime(order.getOrderPlacedTime());
		return orderResponse;
	}

	
	@Override
	public String mapOrderIdentifier(Order order) {
		return "DEL+"+order.getId();
	}
	
	
}
