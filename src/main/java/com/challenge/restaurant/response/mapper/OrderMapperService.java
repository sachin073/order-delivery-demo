package com.challenge.restaurant.response.mapper;

import com.challenge.restaurant.model.Order;
import com.challenge.restaurant.response.model.OrderDetailsResponse;

public interface OrderMapperService {

	OrderDetailsResponse mapOrderDetails(Order order);

	String mapOrderIdentifier(Order order);

}