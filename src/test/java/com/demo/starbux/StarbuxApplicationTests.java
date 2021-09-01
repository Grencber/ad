package com.demo.starbux;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.starbux.controller.Controller;
import com.demo.starbux.domain.cart.CartDrink;
import com.demo.starbux.domain.cart.Topping;
import com.demo.starbux.repositories.CombinationToppingItemRepo;
import com.demo.starbux.repositories.DrinkCombinationRepo;
import com.demo.starbux.repositories.ItemRepo;
import com.demo.starbux.repositories.OrderRepo;
import com.demo.starbux.repositories.implementations.ItemRepoInterfaceImpl;
import com.demo.starbux.service.MenuService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

//@SpringBootTest
@WebMvcTest(Controller.class)
class StarbuxApplicationTests {
	
	@Autowired
    MockMvc mockMvc;
	
	@MockBean
	private MenuService menuService;
	
	@MockBean
	private OrderRepo orderRepo;
	
	@MockBean
	private DrinkCombinationRepo drinkCombinationRepo;

	@MockBean
	private ItemRepo itemRepo;
	
	@MockBean
	private ItemRepoInterfaceImpl itemRepoInterfaceImpl;
	
	@MockBean
	private CombinationToppingItemRepo combinationToppingItemRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testAddInvalidDrinkNameToCart() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Topping topping = new Topping("milk");
		List<Topping> toppingList = new ArrayList<>();
		toppingList.add(topping);
		CartDrink cartDrink = new CartDrink(toppingList , "ocha");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addItem")
				.accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(cartDrink))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(400, response.getStatus());
	}

	@Test
	public void testAddInvalidToppingNameToCart() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Topping topping = new Topping("ilk");
		List<Topping> toppingList = new ArrayList<>();
		toppingList.add(topping);
		CartDrink cartDrink = new CartDrink(toppingList , "mocha");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addItem")
				.accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(cartDrink))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(400, response.getStatus());
	}
}
