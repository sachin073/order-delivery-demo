package com.challenge.restaurant.response.model;

import java.time.LocalDateTime;
import java.util.Date;

public class OrderDetailsResponse {

	String id;
	
	String itemName;

	Integer cost;

	LocalDateTime orderPlacedTime;

	String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public LocalDateTime getOrderPlacedTime() {
		return orderPlacedTime;
	}

	public void setOrderPlacedTime(LocalDateTime orderPlacedTime) {
		this.orderPlacedTime = orderPlacedTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
