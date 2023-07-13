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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.dto.StateDto;
import com.bookinventorymanagement.exceptions.StateNotFoundException;
import com.bookinventorymanagement.service.StateService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StateControllerTest {


	 @Autowired
	 private MockMvc mockMvc;
	
	@MockBean
	private StateService stateService;

	@BeforeEach
	public void setupAuthentication() {
		Authentication auth = new UsernamePasswordAuthenticationToken("Aadesh_d", "123",
				Collections.singletonList(new SimpleGrantedAuthority("Admin")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	@Test
	public void testSaveState() throws Exception
	{
		StateDto stateDto=new StateDto();
		stateDto.setStateCode("MH");
		stateDto.setStateName("Maharashtra");
		ResponseDto responseDto=new ResponseDto();
		responseDto.setResponseMessage("State added successfully");
		when(stateService.saveState(stateDto)).thenReturn(responseDto);
		 mockMvc.perform(post("/state/add")
	        		.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(stateDto)))
	        		.andExpect(status().isOk());

					
	        
	        verify(stateService, times(1)).saveState(any(StateDto.class));
		
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
	public void testGetStateById() throws Exception
	{
		String stateCode="AK";
		StateDto stateDto=new StateDto();
		stateDto.setStateCode(stateCode);
		when(stateService.getStateById(stateCode)).thenReturn(stateDto);
        
        
        mockMvc.perform(get("/state/{stateCode}",stateCode))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.stateCode", is("AK")));
		
				
        
        verify(stateService, times(1)).getStateById(stateCode);

	}
	
	@Test
	public void testUpdateStateNameById() throws Exception
	{
		String stateCode="MH";
		StateDto stateDto =new StateDto();
		stateDto.setStateCode(stateCode);
		stateDto.setStateName("Maharashtra");
		
		StateDto updateStateDto=new StateDto();
		updateStateDto.setStateCode(stateCode);
		updateStateDto.setStateName("Manipur");
		
		when(stateService.updateStateNameById(eq(stateCode), any(StateDto.class))).thenReturn(updateStateDto);
		mockMvc.perform(put("/state/update/name/{stateCode}",stateCode)
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(stateDto)))
        		.andExpect(status().isOk())
				.andExpect(jsonPath("$.stateCode", is("MH")))
				.andExpect(jsonPath("$.stateName", is("Manipur")));
				
        
		verify(stateService, times(1)).updateStateNameById(eq(stateCode),any(StateDto.class));
    	
	
	}
	 @Test
	    public void testGetAllStates() throws Exception {
	        StateDto stateDto = new StateDto();
	       

	        List<StateDto> stateDtoList = Arrays.asList(stateDto);

	        when(stateService.getAllStates()).thenReturn(stateDtoList);

	        mockMvc.perform(MockMvcRequestBuilders.get("/state"))
	                .andExpect(status().isOk());
	        verify(stateService, times(1)).getAllStates();
	    }

	
	 @Test
		public void testSaveStateInvalidInput() throws Exception
		{
		 StateDto stateDto = new StateDto();
		    stateDto.setStateCode(null); // Invalid null value for stateCode
		    stateDto.setStateName("Maharashtra");
		 
		    // Validate the DTO using a Validator
		    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		    Validator validator = factory.getValidator();
		    Set<ConstraintViolation<StateDto>> violations = validator.validate(stateDto);
		 
		    // Expect a violation for the stateCode field being null
		    assertThat(violations)
		            .extracting(ConstraintViolation::getPropertyPath)
		            .extracting(Object::toString)
		            .contains("stateCode");
		 
		    // Perform the test with the invalid DTO
		    mockMvc.perform(post("/state/add")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(asJsonString(stateDto)))
		            .andExpect(status().isBadRequest());
		 
		    verify(stateService, times(0)).saveState(any(StateDto.class)); // Ensure saveState is not called


		}
	 @Test 
		public void testGetStateByIdInvalidId() throws Exception
		{
			String stateCode="00";
			StateDto stateDto=new StateDto();
			stateDto.setStateCode(stateCode);
			when(stateService.getStateById(stateCode))
			.thenThrow(new StateNotFoundException("No State Found By ID:"+stateCode));
	        
	        
	        mockMvc.perform(get("/state/{stateCode}",stateCode))
					.andExpect(status().isNotFound());
			 verify(stateService,times(1)).getStateById(stateCode);

		}
	 @Test
	    public void testGetAllStatesNoStateFound() throws Exception {
	       
	        List<StateDto> stateDtoList = Collections.emptyList();

	        when(stateService.getAllStates()).thenReturn(stateDtoList);

	        mockMvc.perform(MockMvcRequestBuilders.get("/state"))
	                .andExpect(status().isOk());
	        verify(stateService, times(1)).getAllStates();
	    }

	 @Test
		public void testUpdateStateNameByIdInvalidId() throws Exception
		{
			String stateCode="00";
			StateDto stateDto =new StateDto();
			stateDto.setStateCode(stateCode);
			stateDto.setStateName("Maharashtra");
			
			StateDto updateStateDto=new StateDto();
			updateStateDto.setStateCode(stateCode);
			updateStateDto.setStateName("Manipur");
			
			when(stateService.updateStateNameById(eq(stateCode), any(StateDto.class)))
			.thenThrow(new StateNotFoundException("No State Found By ID:"+stateCode));
			mockMvc.perform(put("/state/update/name/{stateCode}",stateCode)
	        		.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(stateDto)))
	        		.andExpect(status().isNotFound());
	        verify(stateService, times(1)).updateStateNameById(eq(stateCode), any(StateDto.class));
		}

}
