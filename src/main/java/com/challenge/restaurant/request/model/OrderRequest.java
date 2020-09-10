package com.challenge.restaurant.request.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

public class OrderRequest {

	@NotNull(message = "name is required")
	@Size(min = 2, max = 30)
    String itemName;

	@NotNull(message = "cost is required")
	@NumberFormat(pattern = "?\\d+(\\.\\d+)?")
    Integer cost;

	@Null
    Date orderPlacedTime;
	
	@NotNull(message = "cost is required")
    String deliveryAddress;

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

	public Date getOrderPlacedTime() {
		return orderPlacedTime;
	}

	public void setOrderPlacedTime(Date orderPlacedTime) {
		this.orderPlacedTime = orderPlacedTime;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
    
    
}
