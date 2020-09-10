package com.challenge.restaurant.response.model;

public class PersonDetailsResponse {
	
	String name;
	
	String phoneNo;
	
	String Vehicle;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getVehicle() {
		return Vehicle;
	}

	public void setVehicle(String vehicle) {
		Vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "PersonDetailsResponse [name=" + name + ", phoneNo=" + phoneNo + ", Vehicle=" + Vehicle + "]";
	}
	
}
