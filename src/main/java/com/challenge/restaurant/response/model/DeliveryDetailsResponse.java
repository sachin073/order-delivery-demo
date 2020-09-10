package com.challenge.restaurant.response.model;

import java.time.LocalDateTime;

import com.challenge.restaurant.model.OrderStatus;

public class DeliveryDetailsResponse {

	String orderId;

	OrderStatus status;

	LocalDateTime timeLeftToDeliver;

	PersonDetailsResponse deliveryBoyDetails;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimeLeftToDeliver() {
		return timeLeftToDeliver;
	}

	public void setTimeLeftToDeliver(LocalDateTime timeLeftToDeliver) {
		this.timeLeftToDeliver = timeLeftToDeliver;
	}

	public PersonDetailsResponse getDeliveryBoyDetails() {
		return deliveryBoyDetails;
	}

	public void setDeliveryBoyDetails(PersonDetailsResponse deliveryBoyDetails) {
		this.deliveryBoyDetails = deliveryBoyDetails;
	}

	@Override
	public String toString() {
		return "DeliveryDetailsResponse [orderId=" + orderId + ", status=" + status + ", timeLeftToDeliver="
				+ timeLeftToDeliver + ", deliveryBoyDetails=" + deliveryBoyDetails + "]";
	}

}
