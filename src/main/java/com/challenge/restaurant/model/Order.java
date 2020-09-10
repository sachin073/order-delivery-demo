package com.challenge.restaurant.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by sachin on 4/7/19.
 */

@Entity
@Table(name = "order_table")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

	@Id
	@GeneratedValue
	Long id;

	@Column
	String itemName;

	@Column
	Integer cost;

	@Column
	LocalDateTime orderPlacedTime;

	@Column
	String status;

	Order() {
	}

	public Order(String itemName) {
		this.itemName = itemName;
	}
	
	public Order(String itemName,int cost) {
		this.itemName = itemName;
		this.cost = cost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getOrderPlacedTime() {
		return orderPlacedTime;
	}

	public void setOrderPlacedTime(LocalDateTime orderPlacedTime) {
		this.orderPlacedTime = orderPlacedTime;
	}
}
