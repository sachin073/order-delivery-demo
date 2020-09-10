package com.challenge.restaurant.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.challenge.restaurant.model.ErrorRespose;

public class OrderValidationUtils {

	public static final String ORDER_NOT_FOUND_MSG = "No order exist by id ";

	public static long parseOrderId(String id) {
		String orderSubs = id.substring(4);
		long oId = 0l;
		try {
			oId = Long.valueOf(orderSubs);
		} catch (NumberFormatException e) {
			new ResponseEntity<ErrorRespose>(new ErrorRespose(ORDER_NOT_FOUND_MSG + id), HttpStatus.NOT_FOUND);
		}
		return oId;
	}
}
