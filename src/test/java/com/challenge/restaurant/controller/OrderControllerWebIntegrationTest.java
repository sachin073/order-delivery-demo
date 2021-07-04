package com.challenge.restaurant.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.challenge.restaurant.RestaurantApplication;
import com.challenge.restaurant.model.Order;
import com.challenge.restaurant.repository.OrderRepository;
import com.challenge.restaurant.request.model.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = RestaurantApplication.class)
@TestPropertySource(locations = "/application-test.properties")
class OrderControllerWebIntegrationTest {

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	ObjectMapper mapper;

	@LocalServerPort
	int port;

	@Autowired
	MockMvc mvc;

	@Test
	@DirtiesContext // create database after insertion
	void placeOrdersTest() throws Exception {

		OrderRequest request = new OrderRequest();
		request.setCost(100);
		request.setItemName("dummy_item");
		request.setDeliveryAddress("Delhi");
		System.out.println(port);

		mvc.perform(MockMvcRequestBuilders.post("/restaurant/order").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))).andExpect(MockMvcResultMatchers.status().isOk());
		mvc.perform(MockMvcRequestBuilders.post("/restaurant/order").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))).andExpect(MockMvcResultMatchers.status().isOk());
		mvc.perform(MockMvcRequestBuilders.post("/restaurant/order").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))).andExpect(MockMvcResultMatchers.status().isOk());

		System.out.println(" Prometheus URL ::: " + "http://localhost:" + port + "/api/actuator/prometheus");
		Thread.sleep(10000000);
		List<Order> orders = orderRepo.findAll();
		assertNotNull(orders);
	}
}
