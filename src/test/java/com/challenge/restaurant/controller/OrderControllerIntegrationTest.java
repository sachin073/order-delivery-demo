package com.challenge.restaurant.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.hamcrest.MockitoHamcrest;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.challenge.restaurant.RestaurantApplication;
import com.challenge.restaurant.model.Order;
import com.challenge.restaurant.repository.OrderRepository;
import com.challenge.restaurant.request.model.OrderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ContextConfiguration(classes = RestaurantApplication.class)
@TestPropertySource(locations = "/application-test.properties")
class OrderControllerIntegrationTest {

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	@DirtiesContext // create database after insertion
	void testPlaceorder() throws Exception {

		OrderRequest request = new OrderRequest();
		request.setCost(100);
		request.setItemName("dummy_item");
		request.setDeliveryAddress("Delhi");

		mvc.perform(MockMvcRequestBuilders.post("/restaurant/order").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))).andExpect(MockMvcResultMatchers.status().isOk());
		List<Order> orders = orderRepo.findAll();
		assertNotNull(orders);
		assertNotNull(orders.get(0));
		assertEquals(orders.get(0).getItemName(), request.getItemName());

	}

	@Test
	@DirtiesContext // create database after insertion
	void testGetStatus() throws Exception {
		testPlaceorder();// create order manually
		List<Order> orders = orderRepo.findAll();

		Long id = orders.get(0).getId();
		mvc.perform(MockMvcRequestBuilders.get("/restaurant/orders/DEL+" + id)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().json("{id : DEL+"+id+", itemName : dummy_item}"));
				

	}
	
}
