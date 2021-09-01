package com.demo.starbux;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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

import com.demo.starbux.controller.ControllerUser;
import com.demo.starbux.domain.Item;
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
@WebMvcTest(ControllerUser.class)
@ExtendWith(MockitoExtension.class)
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
	
	private final String cartIsAddedToCart = "item is added to cart";
	
	
	
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
		Mockito.when(menuService.addItem(Mockito.anyObject())).thenReturn(null);
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
		Mockito.when(menuService.addItem(Mockito.anyObject())).thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addItem")
				.accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(cartDrink))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(400, response.getStatus());
	}
	
	@Test
	public void testAddOnlyDrinkToCart() throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Topping topping = new Topping("milk");
		List<Topping> toppingList = new ArrayList<>();
		toppingList.add(topping);
		CartDrink cartDrink = new CartDrink("mocha");
		Mockito.when(menuService.addItem(Mockito.anyObject())).thenReturn(Mockito.anyString());
		System.out.println("-----------------" + objectMapper.writeValueAsString(cartDrink));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addItem")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cartDrink));
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("response " + response.getContentAsString());
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void addItemRetunsNullWhenCartItemIsNull() {
		menuService = new MenuService();
		String addItemResult = menuService.addItem(null);
		assertEquals(addItemResult, null);
	}
	
	@Test
	public void addItemRetunsSuccessStringWhenCartItemIsNotNull() {
		Topping topping = new Topping("milk");
		List<Topping> toppingList = new ArrayList<>();
		toppingList.add(topping);
		CartDrink cartDrink = new CartDrink("mocha");
		
		Item item = new Item("lemon","topping");
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(item);
		Mockito.when(itemRepo.findAll()).thenReturn(itemList);
		menuService = new MenuService();
	
		
		String addItemResult = menuService.addItem(cartDrink);
		assertEquals(addItemResult, cartIsAddedToCart);
	}
}
