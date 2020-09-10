/**
 * 
 */
package com.challenge.restaurant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
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

	@InjectMocks
	OrderServiceImpl service; // class to test
	
	@Mock
	OrderRepository orderRepo; // test class dependency mocked

	@BeforeAll
	private static void setupTestEnv() {
		System.out.println(" Strting Tests : OrderServiceUnitTest -- Total tests : 3 ");
	}
	
	@BeforeEach
	private void refreshStub() throws JsonParseException, JsonMappingException, IOException {
		File file = ResourceUtils.getFile("classpath:order.json");
		ObjectMapper mapper = new ObjectMapper();
		orderStub = mapper.readValue(file, Order.class);
		System.out.println(" Refresh order object");
		MockitoAnnotations.initMocks(this); 	// Initialize mocks 
	}

	@Test
	@org.junit.jupiter.api.Order(1)
	void testCreateOrder() {
		//mock save method 
		Mockito.when(orderRepo.save(Mockito.any())).thenReturn(orderStub);
		Order createdOrder = service.createOrder(orderStub);
		
		assertEquals(orderStub, createdOrder);
		System.out.println(" Create order test : pass");
		
	}

	@Test
	@org.junit.jupiter.api.Order(3)
	void testInvalidOrderSearch() {
		
		assertThrows(IllegalOrderException.class, () ->{
			//mock find method 
			
			Mockito.when(orderRepo.findOrderById(Mockito.anyLong())).thenReturn(null);
			service.searchOrder(2);			
		}, OrderValidationUtils.ORDER_NOT_FOUND_MSG+2);
		System.out.println(" Invalid order search test : pass");

	}
	
	@Test()
	@org.junit.jupiter.api.Order(2)
	void testSearchOrder() {
		Mockito.when(orderRepo.findOrderById(Mockito.anyLong())).thenReturn(orderStub);
		Order order = service.searchOrder(1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(order);
		assertEquals(orderStub, order);
		System.out.println(" Order search test : pass");

	}
	
	@Test()
	@Timeout(unit = TimeUnit.SECONDS, value = 1)
	@Disabled // remove to run
	void testSearchOrderPerformace() {
		Mockito.when(orderRepo.findOrderById(Mockito.anyLong())).thenReturn(orderStub);
		Order order = service.searchOrder(1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test()
	@org.junit.jupiter.api.Order(4)
	@Disabled
	void testdestoryOrder() {
		// TODO : test destroy
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
