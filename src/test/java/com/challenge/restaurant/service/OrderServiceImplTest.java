/**
 * 
 */
package com.challenge.restaurant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.ResourceUtils;

import com.challenge.restaurant.exception.IllegalOrderException;
import com.challenge.restaurant.model.Order;
import com.challenge.restaurant.repository.OrderRepository;
import com.challenge.restaurant.utils.OrderValidationUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author SVerma2
 *
 */
class OrderServiceImplTest {

	Order orderStub;

	@BeforeAll
	private static void setupTestEnv() {
		System.out.println(" Strting Tests : OrderServiceUnitTest -- Total tests : 3 ");
	}
	
	@InjectMocks
	OrderServiceImpl service; // class to test
	
	@Mock
	OrderRepository orderRepo; // test class dependency mocked

	
	@BeforeEach
	private void refreshStub() throws JsonParseException, JsonMappingException, IOException {
		File file = ResourceUtils.getFile("classpath:order.json");
		ObjectMapper mapper = new ObjectMapper();
		orderStub = mapper.readValue(file, Order.class);
		System.out.println(" Refresh order object");
		MockitoAnnotations.initMocks(this); 	// Initialize mocks 
	}

	@Test
	void testCreateOrder() {
		
		Mockito.when(orderRepo.save(Mockito.any())).thenReturn(orderStub);
		
		Order createdOrder = service.createOrder(orderStub);
		
		assertEquals(orderStub, createdOrder);
		System.out.println(" Create order test : pass");
		
	}

	@Test
	void testInvalidOrderSearch() {
		
		assertThrows(IllegalOrderException.class, () ->{
		
			Mockito.when(orderRepo.findOrderById(Mockito.anyLong())).thenReturn(null);
			service.searchOrder(2);			
		}, OrderValidationUtils.ORDER_NOT_FOUND_MSG+2);
		System.out.println(" Invalid order search test : pass");

	}
	
	@Test()
	void testSearchOrder() {
		Mockito.when(orderRepo.findOrderById(Mockito.anyLong())).thenReturn(orderStub);
		Order order = service.searchOrder(1);
		assertNotNull(order);
		assertEquals(orderStub, order);
		System.out.println(" Order search test : pass");

	}

	@AfterEach
	private void destroyStub() {
		orderStub = null;
		System.out.println(" destroy order object \n");
	}

	@AfterAll
	private static void destroyTestEvn() {
		System.out.println(" Tests Completed : OrderServiceUnitTest -- Total tests : 3 ");

	}

}
