package com.bookinventorymanagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bookinventorymanagement.dto.AuthorDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.dto.UserDto;
import com.bookinventorymanagement.exceptions.InvalidInputException;
import com.bookinventorymanagement.exceptions.UserNotFoundException;
import com.bookinventorymanagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setupAuthentication() {
		Authentication auth = new UsernamePasswordAuthenticationToken("Aadesh_d", "123",
				Collections.singletonList(new SimpleGrantedAuthority("Admin")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Test
	public void testSaveUser() throws Exception {
		UserDto userDto = new UserDto();
		userDto.setUserId(1000015);
		userDto.setLastName("Dakhode");
		userDto.setFirstName("Aadesh");
		userDto.setPhoneNumber("8999343500");
		userDto.setUserName("Aadesh@gmail,com");
		userDto.setPassword("Aadesh@123");
		userDto.setRoleNumber(1);
		ResponseDto responseDto=new ResponseDto();
		responseDto.setResponseMessage("User added successfully");
		when(userService.saveUser(userDto)).thenReturn(responseDto);

		mockMvc.perform(post("/user/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(userDto)))
				.andExpect(status().isOk());


		verify(userService, times(1)).saveUser(any(UserDto.class));
	}

	public static String asJsonString(Object object) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Test
	public void testGetUserById() throws Exception
	{
		Integer userId=1000015;
		UserDto userDto=new UserDto();
		userDto.setUserId(userId);
		
		when(userService.getUserById(userId)).thenReturn(userDto);
		 mockMvc.perform(get("/user/{userId}",userId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId", is(1000015)));

		 verify(userService, times(1)).getUserById(userId);
	}
	
	@Test
	public void testUpdateFirstNameById() throws Exception
	{
		Integer userId=1000015;
		UserDto userDto=new UserDto();
		userDto.setUserId(userId);
		userDto.setFirstName("Manager");
		
		UserDto updateUserDto=new UserDto();
		updateUserDto.setUserId(userId);
		updateUserDto.setFirstName("Steve");
		
		when(userService.updateFirstNameById(eq(userId),any (UserDto.class))).thenReturn(updateUserDto);
		mockMvc.perform(put("/user/update/firstname/{userId}",userId)
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(userDto)))
        		.andExpect(status().isOk())
				.andExpect(jsonPath("$.userId", is(1000015)))
				.andExpect(jsonPath("$.firstName", is("Steve")));
		verify(userService,times(1)).updateFirstNameById(eq(userId),any (UserDto.class));
	}
	
	
	@Test
	public void testUpdateLastNameById() throws Exception
	{
		Integer userId=1000015;
		UserDto userDto=new UserDto();
		userDto.setUserId(userId);
		userDto.setLastName("Slack");
		
		UserDto updateUserDto=new UserDto();
		updateUserDto.setUserId(userId);
		updateUserDto.setLastName("Smith");
		
		when(userService.updateLastNameById(eq(userId),any (UserDto.class))).thenReturn(updateUserDto);
		mockMvc.perform(put("/user/update/lastname/{userId}",userId)
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(userDto)))
        		.andExpect(status().isOk())
				.andExpect(jsonPath("$.userId", is(1000015)))
				.andExpect(jsonPath("$.lastName", is("Smith")));
		verify(userService,times(1)).updateLastNameById(eq(userId),any (UserDto.class));
	}
	
	@Test
	public void testUpdatePhoneNumberById() throws Exception
	{
		Integer userId=1000015;
		UserDto userDto=new UserDto();
		userDto.setUserId(userId);
		userDto.setPhoneNumber("(593) 555-2443");
		
		UserDto updateUserDto=new UserDto();
		updateUserDto.setUserId(userId);
		updateUserDto.setPhoneNumber("8999343500");
		
		when(userService.updatePhoneNumberById(eq(userId),any (UserDto.class))).thenReturn(updateUserDto);
		mockMvc.perform(put("/user/update/phonenumber/{userId}",userId)
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(userDto)))
        		.andExpect(status().isOk())
				.andExpect(jsonPath("$.userId", is(1000015)))
				.andExpect(jsonPath("$.phoneNumber", is("8999343500")));
		verify(userService,times(1)).updatePhoneNumberById(eq(userId),any(UserDto.class));
	}
	
	@Test
	public void testSaveUserInvalidInput() throws Exception {
		UserDto userDto = new UserDto();
		userDto.setUserId(0);
		userDto.setLastName("Dakhode");
		userDto.setFirstName("Aadesh");
		userDto.setPhoneNumber("1234567891011121212");
		userDto.setUserName("aadesh_d");
		userDto.setPassword("123");
		userDto.setRoleNumber(-1);
		 ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		    Validator validator = factory.getValidator();
		    Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

		    // Expect a violation for the firstName field being null

		    assertThat(violations)
		            .extracting(ConstraintViolation::getPropertyPath)
		            .extracting(Object::toString)
		            .contains("phoneNumber");
		    		

		    // Perform the test with the invalid DTO
		
		when(userService.saveUser(userDto)).thenThrow(new InvalidInputException("Invalid User Details!!"));

		mockMvc.perform(post("/user/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(userDto)))
				.andExpect(status().isBadRequest());
		verify(userService,times(0)).saveUser(userDto);

	}
	@Test
	public void testGetUserByIdInvalidId() throws Exception
	{
		UserDto userDto = new UserDto();
	    userDto.setUserId(1000015);
	    userDto.setLastName("Dakhode");
	    userDto.setFirstName("Aadesh");
	    userDto.setPhoneNumber("8999343500");
	    userDto.setUserName("Aadesh@gmail,comasssssddasdfghjkjhxcvbgfdsdfghjkjhgfdsdfghgf"); // Invalid username format
	    userDto.setPassword("Aadesh@123");
	    userDto.setRoleNumber(1);

	 

	    // Validate the DTO using a Validator
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
	    Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

	 

	    // Expect a violation for the userName field having an invalid email format
	    assertThat(violations)
	            .extracting(ConstraintViolation::getPropertyPath)
	            .extracting(Object::toString)
	            .contains("userName");

	 

	    // Perform the test with the invalid DTO
	    mockMvc.perform(post("/user/post")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(userDto)))
	            .andExpect(status().isBadRequest());

	 

	    verify(userService, times(0)).saveUser(any(UserDto.class)); // Ensure saveUser is not called
	}
	
	
	@Test
	public void testUpdateFirstNameByIdInvalidId() throws Exception
	{
		Integer userId=-1000015;
		UserDto userDto=new UserDto();
		userDto.setUserId(userId);
		userDto.setFirstName("Manager");
		
		UserDto updateUserDto=new UserDto();
		updateUserDto.setUserId(userId);
		updateUserDto.setFirstName("Steve");
		
		when(userService.updateFirstNameById(eq(userId),any (UserDto.class)))
		.thenThrow(new UserNotFoundException("No User Found By Id:"+userId));
		mockMvc.perform(put("/user/update/firstname/{userId}",userId)
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(userDto)))
        		.andExpect(status().isNotFound());
				
		verify(userService,times(1)).updateFirstNameById(eq(userId),any (UserDto.class));
	}
	
	@Test
	public void testUpdateLastNameByIdInvalidId() throws Exception
	{
		Integer userId=-1000015;
		UserDto userDto=new UserDto();
		userDto.setUserId(userId);
		userDto.setLastName("Slack");
		
		UserDto updateUserDto=new UserDto();
		updateUserDto.setUserId(userId);
		updateUserDto.setLastName("Smith");
		
		when(userService.updateLastNameById(eq(userId),any (UserDto.class)))
		.thenThrow(new UserNotFoundException("No User Found By Id:"+userId));
		mockMvc.perform(put("/user/update/lastname/{userId}",userId)
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(userDto)))
        		.andExpect(status().isNotFound());
				
		verify(userService,times(1)).updateLastNameById(eq(userId),any (UserDto.class));
	}
	@Test
	public void testUpdatePhoneNumberByIdInvalidId() throws Exception
	{
		Integer userId=1000015;
		UserDto userDto=new UserDto();
		userDto.setUserId(userId);
		userDto.setPhoneNumber("(593) 555-2443");
		
		UserDto updateUserDto=new UserDto();
		updateUserDto.setUserId(userId);
		updateUserDto.setPhoneNumber("8999343500");
		
		when(userService.updatePhoneNumberById(eq(userId),any (UserDto.class)))
		.thenThrow(new UserNotFoundException("No User Found By Id:"+userId));
		mockMvc.perform(put("/user/update/phonenumber/{userId}",userId)
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(userDto)))
        		.andExpect(status().isNotFound());
				
		verify(userService,times(1)).updatePhoneNumberById(eq(userId),any(UserDto.class));
	}
}
