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

import com.bookinventorymanagement.dto.PermRoleDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.exceptions.CategoryNotFoundException;
import com.bookinventorymanagement.exceptions.PermRoleNotFoundException;
import com.bookinventorymanagement.service.PermRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PermRoleControllerTest {

	@MockBean
	private PermRoleService permRoleService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setupAuthentication() {
		Authentication auth = new UsernamePasswordAuthenticationToken("Aadesh_d", "123",
				Collections.singletonList(new SimpleGrantedAuthority("Admin")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	@Test
	public void  testSavePermRole() throws Exception
	{
		PermRoleDto permRoleDto=new PermRoleDto();
		permRoleDto.setRoleNumber(5);
		permRoleDto.setPermRole("Admin");
		ResponseDto responseDto=new ResponseDto();
		responseDto.setResponseMessage("permissionrole added successfully");
		when(permRoleService.savePermRole(permRoleDto)).thenReturn(responseDto);
		 mockMvc.perform(post("/permrole/post")
	        		.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(permRoleDto)))
	        		.andExpect(status().isOk());
	        
	        verify(permRoleService, times(1)).savePermRole(any(PermRoleDto.class));
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
		public void testGetPermRoleById() throws Exception
		{
			Integer roleNumber=1;
			PermRoleDto permRoleDto=new PermRoleDto();
			permRoleDto.setRoleNumber(roleNumber);
			
			when(permRoleService.getPermRoleById(roleNumber)).thenReturn(permRoleDto);
			
			 
	        mockMvc.perform(get("/permrole/{rollNumber}",roleNumber))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.roleNumber", is(1)));

	        verify(permRoleService, times(1)).getPermRoleById(roleNumber);

		}
		
		@Test
		public void testUpdatePermRole() throws Exception
		{
			Integer roleNumber=1;
			PermRoleDto permRoleDto=new PermRoleDto();
			permRoleDto.setRoleNumber(roleNumber);
			permRoleDto.setPermRole("Admin");
			
			PermRoleDto updatePermRoleDto=new PermRoleDto();
			updatePermRoleDto.setRoleNumber(roleNumber);
			updatePermRoleDto.setPermRole("Guest");
			when(permRoleService.updatePermRoleById(eq(roleNumber),any( PermRoleDto.class))).thenReturn(updatePermRoleDto);
			
			mockMvc.perform(put("/permrole/update/permrole/{rolenumber}",roleNumber)
	        		.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(permRoleDto)))
	        		.andExpect(status().isOk())
					.andExpect(jsonPath("$.roleNumber", is(1)))
					.andExpect(jsonPath("$.permRole", is("Guest")));
	        verify(permRoleService, times(1)).updatePermRoleById(eq(roleNumber), any(PermRoleDto.class));

		}
		@Test
		public void  testSavePermRoleInvalidInput() throws Exception
		{
			PermRoleDto permRoleDto = new PermRoleDto();
		    permRoleDto.setRoleNumber(5);
		    permRoleDto.setPermRole(null); // Invalid null value for PermRole
		    ResponseDto responseDto = new ResponseDto();
		    responseDto.setResponseMessage("Error: Failed to add permission role");

		
		    // Validate the DTO using a Validator
		    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		    Validator validator = factory.getValidator();
		    Set<ConstraintViolation<PermRoleDto>> violations = validator.validate(permRoleDto);

		
		    // Expect a violation for the permRole field being null
		    assertThat(violations)
		            .extracting(ConstraintViolation::getPropertyPath)
		            .extracting(Object::toString)
		            .contains("permRole");

		    // Perform the test with the invalid DTO
		    mockMvc.perform(post("/permrole/post")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(asJsonString(permRoleDto)))
		            .andExpect(status().isBadRequest());

		    verify(permRoleService, times(0)).savePermRole(any(PermRoleDto.class)); // Ensure savePermRole is not called
	}

		@Test
		public void testGetPermRoleByIdInvalidId() throws Exception
		{
			Integer roleNumber=-1;
			PermRoleDto permRoleDto=new PermRoleDto();
			permRoleDto.setRoleNumber(roleNumber);
			
			when(permRoleService.getPermRoleById(roleNumber))
			.thenThrow(new PermRoleNotFoundException("No PermRole Found with id:"+roleNumber));
	        mockMvc.perform(get("/permrole/{rollNumber}",roleNumber))
	        .andExpect(status().isNotFound());

	verify(permRoleService, times(1)).getPermRoleById(roleNumber);

}
		@Test
		public void testUpdatePermRoleInvalidId() throws Exception
		{
			Integer roleNumber=-1;
			PermRoleDto permRoleDto=new PermRoleDto();
			permRoleDto.setRoleNumber(roleNumber);
			permRoleDto.setPermRole("Admin");
			
			PermRoleDto updatePermRoleDto=new PermRoleDto();
			updatePermRoleDto.setRoleNumber(roleNumber);
			updatePermRoleDto.setPermRole("Guest");
			
			when(permRoleService.updatePermRoleById(eq(roleNumber),any (PermRoleDto.class)))
			.thenThrow(new CategoryNotFoundException("PermRole not found with Id:"+roleNumber));

			mockMvc.perform(put("/permrole/update/permrole/{rolenumber}",roleNumber)	    			.contentType(MediaType.APPLICATION_JSON)
	    			.content(asJsonString(permRoleDto)))
	    			.andExpect(status().isNotFound());

			verify(permRoleService, times(1)).updatePermRoleById(eq(roleNumber),any (PermRoleDto.class));
	}
}
