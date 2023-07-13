package com.bookinventorymanagement;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.bookinventorymanagement.dto.InventoryDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.exceptions.InventoryNotFoundException;
import com.bookinventorymanagement.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class InventoryControllerTest {

	@MockBean
	private InventoryService inventoryService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setupAuthentication() {
		Authentication auth = new UsernamePasswordAuthenticationToken("Aadesh_d", "123",
				Collections.singletonList(new SimpleGrantedAuthority("Admin")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	@Test
	public void testAddInventory() throws Exception
	{
		InventoryDto inventoryDto=new InventoryDto();
		inventoryDto.setInventoryId(1000065);
		inventoryDto.setIsbn("1-111-11111-1");
		inventoryDto.setRanks(1);
		inventoryDto.setPurchased(((byte) 0));
		ResponseDto responseDto=new ResponseDto();
		responseDto.setResponseMessage("Book inventory added successfully");
		
		when(inventoryService.saveInventory(inventoryDto)).thenReturn(responseDto);
		 mockMvc.perform(post("/inventory/add")
	        		.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(inventoryDto)))
	        		.andExpect(status().isOk());
	        
	        verify(inventoryService, times(1)).saveInventory(inventoryDto);
		
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
	public void testGetInventoryById() throws Exception
	{
		Integer inventoryId=1000000;
		InventoryDto inventoryDto=new InventoryDto();
		inventoryDto.setInventoryId(inventoryId);
		
		when(inventoryService.getInventoryById(inventoryId)).thenReturn(inventoryDto);
		 mockMvc.perform(get("/inventory/{inventoryId}",inventoryId))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$.inventoryId").value(1000000));	

		 verify(inventoryService, times(1)).getInventoryById(inventoryId);

	}
	
	@Test
	public void testUpdatePurchasedById() throws Exception
	{
		Integer inventoryId=1000000;
		InventoryDto inventoryDto=new InventoryDto();
		inventoryDto.setInventoryId(inventoryId);
		inventoryDto.setPurchased(((byte) 0));	
	
		InventoryDto updateDto=new InventoryDto();
		updateDto.setInventoryId(inventoryId);
		updateDto.setPurchased(((byte) 1));	
		
		when(inventoryService.updatePurchasedById(eq(inventoryId),any (InventoryDto.class))).thenReturn(updateDto);
		mockMvc.perform(put("/inventory/update/{inventoryId}",inventoryId)
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(inventoryDto)))
        		.andExpect(status().isOk())
        		 .andExpect(jsonPath("$.inventoryId").value(1000000))
                 .andExpect(jsonPath("$.purchased").value(1));	

				
        
    	verify(inventoryService, times(1)).updatePurchasedById(eq(inventoryId), any(InventoryDto.class));
	}
	
	@Test
	public void testAddInventoryInvaliInput() throws Exception
	{
		  InventoryDto inventoryDto = new InventoryDto();
		    inventoryDto.setInventoryId(1000065);
		    inventoryDto.setIsbn(null); // Invalid null value for ISBN
		    inventoryDto.setRanks(1);
		    inventoryDto.setPurchased(((byte) 0));

		 

		    // Validate the DTO using a Validator
		    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		    Validator validator = factory.getValidator();
		    Set<ConstraintViolation<InventoryDto>> violations = validator.validate(inventoryDto);

		 

		    // Expect a violation for the isbn field being null
		    assertThat(violations)
		            .extracting(ConstraintViolation::getPropertyPath)
		            .extracting(Object::toString)
		            .contains("isbn");

		 

		    // Perform the test with the invalid DTO
		    mockMvc.perform(post("/inventory/add")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(asJsonString(inventoryDto)))
		            .andExpect(status().isBadRequest());

		 

		    verify(inventoryService, times(0)).saveInventory(any(InventoryDto.class)); // Ensure saveInventory is not called
}
	@Test
	public void testGetInventoryByIdInvalidId() throws Exception
	{
		Integer inventoryId=-1000000;
		InventoryDto inventoryDto=new InventoryDto();
		inventoryDto.setInventoryId(inventoryId);
		
		when(inventoryService.getInventoryById(inventoryId))
		.thenThrow(new InventoryNotFoundException("No Book InVentory Found!"));
		 mockMvc.perform(get("/inventory/{inventoryId}",inventoryId))
		 .andExpect(status().isNotFound());

		verify(inventoryService, times(1)).getInventoryById(inventoryId);

}
	@Test
	public void testUpdatePurchasedByIdInvalidId() throws Exception
	{
		Integer inventoryId=1000000;
		InventoryDto inventoryDto=new InventoryDto();
		inventoryDto.setInventoryId(inventoryId);
		inventoryDto.setPurchased(((byte) 0));	
	
		InventoryDto updateDto=new InventoryDto();
		updateDto.setInventoryId(inventoryId);
		updateDto.setPurchased(((byte) 1));	
		when(inventoryService.updatePurchasedById(eq(inventoryId),any(InventoryDto.class)))
		.thenThrow(new InventoryNotFoundException("Book Inventory not found with Id:"+inventoryId));

		mockMvc.perform(put("/inventory/update/{inventoryId}",inventoryId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(inventoryDto)))
				.andExpect(status().isNotFound());

		verify(inventoryService, times(1)).updatePurchasedById(eq(inventoryId),any(InventoryDto.class));
}

}
