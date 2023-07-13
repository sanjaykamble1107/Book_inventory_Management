//package com.bookinventorymanagement;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.CoreMatchers.is;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.bookinventorymanagement.dto.ShoppingCartDto;
//import com.bookinventorymanagement.exceptions.ShoppingCartNotFoundException;
//import com.bookinventorymanagement.service.ShoppingCartService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.Validation;
//import jakarta.validation.Validator;
//import jakarta.validation.ValidatorFactory;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//class ShoppingCartControllerTest {
//
//	@MockBean
//	private ShoppingCartService cartService;
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@BeforeEach
//	public void setupAuthentication() {
//		Authentication auth = new UsernamePasswordAuthenticationToken("Aadesh_d", "123",
//				Collections.singletonList(new SimpleGrantedAuthority("Admin")));
//		SecurityContextHolder.getContext().setAuthentication(auth);
//	}
//	@Test
//	public void testSaveShoppingCart() throws Exception
//	{
//		ShoppingCartDto cartDto=new ShoppingCartDto();
//		cartDto.setIsbn("1-111-11111-1");
//		cartDto.setUserId(1000001);
//		
//		String response="Shopping cart added successfully";
//		when(cartService.saveShoppingCart(cartDto)).thenReturn(response);
//		mockMvc.perform(post("/shoppingcart/post")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(asJsonString(cartDto)))
//				.andExpect(status().isOk())
//	            .andExpect(MockMvcResultMatchers.content().string(response));
//
//				
//
//		verify(cartService, times(1)).saveShoppingCart(cartDto);
//	}
//	public static String asJsonString(Object object) {
//		try {
//			ObjectMapper objectMapper = new ObjectMapper();
//			return objectMapper.writeValueAsString(object);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//	
//	@Test
//	public void testGetShoppinCartById() throws Exception
//	{
//		Integer userId=1000000;
//		ShoppingCartDto cartDto=new ShoppingCartDto();
//		cartDto.setUserId(userId);
//		
//		List<ShoppingCartDto> cartList=Arrays.asList(cartDto);
//		when(cartService.getShoppingCartByUserId(userId)).thenReturn(cartList);
//		 mockMvc.perform(get("/shoppingcart/{userId}",userId))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$[0].userId", is(1000000)));
//
//		 verify(cartService, times(1)).getShoppingCartByUserId(userId);
//		
//		
//	}
//	
//	@Test
//	public void testUpdateIsbnById() throws Exception
//	{
//		Integer userId=1000000;
//		ShoppingCartDto cartDto=new ShoppingCartDto();
//		cartDto.setUserId(userId);
//		cartDto.setIsbn("1-111-11111-4");
//		
//		ShoppingCartDto updateCartDto=new ShoppingCartDto();
//		updateCartDto.setUserId(userId);
//		updateCartDto.setIsbn("1-111-11111-5");
//		List<ShoppingCartDto> cartList=Arrays.asList(updateCartDto);
//
//		when(cartService.updateIsbnByUserId(eq(userId),any(ShoppingCartDto.class))).thenReturn(cartList);
//		mockMvc.perform(put("/shoppingcart/update/isbn/{userId}",userId)
//        		.contentType(MediaType.APPLICATION_JSON)
//				.content(asJsonString(cartDto)))
//        		.andExpect(status().isOk())
//				.andExpect(jsonPath("$[0].userId", is(1000000)))
//				.andExpect(jsonPath("$[0].isbn").value("1-111-11111-5"));
//		verify(cartService,times(1)).updateIsbnByUserId(eq(userId), any(ShoppingCartDto.class));
//	}
//	
//	@Test
//	public void testSaveShoppingCartInvalidInput() throws Exception
//	{ShoppingCartDto cartDto = new ShoppingCartDto();
//    cartDto.setIsbn("1-111-11111-1");
//    cartDto.setUserId(null); // Invalid value for userId
// 
//    // Validate the DTO using a Validator
//    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//    Validator validator = factory.getValidator();
//    Set<ConstraintViolation<ShoppingCartDto>> violations = validator.validate(cartDto);
// 
//    // Expect a violation for the userId field being invalid
//    assertThat(violations)
//            .extracting(ConstraintViolation::getPropertyPath)
//            .extracting(Object::toString)
//            .contains("userId");
// 
//    // Perform the test with the invalid DTO
//    mockMvc.perform(post("/shoppingcart/post")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(asJsonString(cartDto)))
//            .andExpect(status().isBadRequest());
// 
//    verify(cartService, times(0)).saveShoppingCart(any(ShoppingCartDto.class)); // Ensure saveShoppingCart is not called
//
//
//			}
//	
//	@Test
//	public void testGetShoppinCartByIdInvalidId() throws Exception
//	{
//		Integer userId=-1000000;
//		ShoppingCartDto cartDto=new ShoppingCartDto();
//		cartDto.setUserId(userId);
//		
//		when(cartService.getShoppingCartByUserId(userId)).thenThrow(new ShoppingCartNotFoundException("No ShoppingCart Found By id:"+userId));
//		 mockMvc.perform(get("/shoppingcart/{userId}",userId))
//			.andExpect(status().isNotFound());
//			verify(cartService, times(1)).getShoppingCartByUserId(userId);
//	}
//	
//	@Test
//	public void testUpdateIsbnByIdInvalidId() throws Exception
//	{
//		Integer userId=-1000000;
//		ShoppingCartDto cartDto=new ShoppingCartDto();
//		cartDto.setUserId(userId);
//		cartDto.setIsbn("1-111-11111-4");
//		
//		ShoppingCartDto updateCartDto=new ShoppingCartDto();
//		updateCartDto.setUserId(userId);
//		updateCartDto.setIsbn("1-111-11111-5");
//
//		when(cartService.updateIsbnByUserId(eq(userId),any(ShoppingCartDto.class)))
//		.thenThrow(new ShoppingCartNotFoundException("No ShoppingCart Found By id:"+userId));		mockMvc.perform(put("/shoppingcart/update/isbn/{userId}",userId)
//        		.contentType(MediaType.APPLICATION_JSON)
//				.content(asJsonString(cartDto)))
//        		.andExpect(status().isNotFound());
//		verify(cartService, times(1)).updateIsbnByUserId(eq(userId),any(ShoppingCartDto.class));
//
//	}
//}
