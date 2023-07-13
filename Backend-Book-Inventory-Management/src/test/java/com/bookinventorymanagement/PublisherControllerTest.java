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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bookinventorymanagement.dto.PublisherDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.exceptions.PublisherNotFoundException;
import com.bookinventorymanagement.service.PublisherService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PublisherControllerTest {

	@MockBean
	private PublisherService publisherService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setupAuthentication() {
		Authentication auth = new UsernamePasswordAuthenticationToken("Aadesh_d", "123",
				Collections.singletonList(new SimpleGrantedAuthority("Admin")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	@Test
	public void testSavePublisher() throws Exception
	{
		PublisherDto  publisherDto=new PublisherDto();
		publisherDto.setPublisherId(14);
		publisherDto.setName("Aadesh");
		publisherDto.setCity("Amravati");
		publisherDto.setStateCode("MH");
		ResponseDto responseDto=new  ResponseDto();
		responseDto.setResponseMessage("Publisher  added successfully");
		when(publisherService.savePublisher(publisherDto)).thenReturn(responseDto);
		
		  mockMvc.perform(post("/publisher/post")
	        		.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(publisherDto)))
	        		.andExpect(status().isOk());

	        
	        verify(publisherService, times(1)).savePublisher(any(PublisherDto.class));
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
	public void testGetAllPublishers() throws Exception
	{
		PublisherDto publisherDto=new PublisherDto();
		
		List<PublisherDto> publisherList=Arrays.asList(publisherDto);
		when(publisherService.getAllPublishers()).thenReturn(publisherList);
		  
		mockMvc.perform(MockMvcRequestBuilders.get("/publisher"))
          .andExpect(status().isOk());
		verify(publisherService, times(1)).getAllPublishers();
	}
	
	@Test
	public void testGetPublisherById() throws Exception
	{
		Integer publisherId=1;
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setPublisherId(publisherId);
		
		when(publisherService.getPublisherById(publisherId)).thenReturn(publisherDto);
		
		mockMvc.perform(get("/publisher/{publisherId}",publisherId))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.publisherId", is(1)));

		verify(publisherService, times(1)).getPublisherById(publisherId);

	}
	
	@Test
	public void testGetPublisherByName() throws Exception
	{
		String name="CovertoCover Publishing";
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setName(name);
		
		when(publisherService.getPublisherByName(name)).thenReturn(publisherDto);
		
		mockMvc.perform(get("/publisher/name/{name}",name))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is("CovertoCover Publishing")));

		verify(publisherService, times(1)).getPublisherByName(name);

	}


	@Test
	public void testUpdateNameByPublisherId() throws Exception
	{
		Integer publisherId=1;
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setPublisherId(publisherId);
		publisherDto.setName("CovertoCover Publishing");
		
		PublisherDto updatePublisherDto=new PublisherDto();
		updatePublisherDto.setPublisherId(publisherId);
		updatePublisherDto.setName("Aadesh");
		
		when(publisherService.updateNameByPublisherId(eq(publisherId),any(PublisherDto.class))).thenReturn(updatePublisherDto);
		
		mockMvc.perform(put("/publisher/update/name/{publisherId}",publisherId)
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(publisherDto)))
        		.andExpect(status().isOk())
				.andExpect(jsonPath("$.publisherId", is(1)))
				.andExpect(jsonPath("$.name", is("Aadesh")));
		
		verify(publisherService, times(1)).updateNameByPublisherId(eq(publisherId), any(PublisherDto.class));
		
	}

	@Test
	public void testUpdateCityByPublisherId() throws Exception
	{
		Integer publisherId=1;
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setPublisherId(publisherId);
		publisherDto.setCity("Rochester");
		
		PublisherDto updatePublisherDto=new PublisherDto();
		updatePublisherDto.setPublisherId(publisherId);
		updatePublisherDto.setCity("Amravati");
		
		when(publisherService.updateCityByPublisherId(eq(publisherId),any(PublisherDto.class))).thenReturn(updatePublisherDto);
		
		mockMvc.perform(put("/publisher/update/city/{publisherId}",publisherId)
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(publisherDto)))
        		.andExpect(status().isOk())
				.andExpect(jsonPath("$.publisherId", is(1)))
				.andExpect(jsonPath("$.city", is("Amravati")));
		
		verify(publisherService, times(1)).updateCityByPublisherId(eq(publisherId), any(PublisherDto.class));
		
	}
	
	@Test
	public void testUpdateStateCodeByPublisherId() throws Exception
	{
		Integer publisherId=1;
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setPublisherId(publisherId);
		publisherDto.setStateCode("NY");
		
		PublisherDto updatePublisherDto=new PublisherDto();
		updatePublisherDto.setPublisherId(publisherId);
		updatePublisherDto.setStateCode("MH");
		
		when(publisherService.updateStateCodeByPublisherId(eq(publisherId),any(PublisherDto.class))).thenReturn(updatePublisherDto);
		
		mockMvc.perform(put("/publisher/update/state/{publisherId}",publisherId)
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(publisherDto)))
        		.andExpect(status().isOk())
				.andExpect(jsonPath("$.publisherId", is(1)))
				.andExpect(jsonPath("$.stateCode", is("MH")));
		
		verify(publisherService, times(1)).updateStateCodeByPublisherId(eq(publisherId), any(PublisherDto.class));
		
	}
	
	@Test
	public void testGetAllPublishersByCity() throws Exception
	{
		String city="Rochester";
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setCity(city);
		
		List<PublisherDto> publisherList=Arrays.asList(publisherDto);
		when(publisherService.getAllPublisherByCity(city)).thenReturn(publisherList);
		  
		mockMvc.perform(MockMvcRequestBuilders.get("/publisher/city/{city}",city))
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value(city));
		verify(publisherService, times(1)).getAllPublisherByCity(city);
		
	}
	
	@Test
	public void testGetAllPublishersByStateCode() throws Exception
	{
		String stateCode="NY";
		PublisherDto publisherDto=new PublisherDto();
				publisherDto.setStateCode(stateCode);
		
		List<PublisherDto> publisherList=Arrays.asList(publisherDto);
		when(publisherService.getAllPublisherByStateCode(stateCode)).thenReturn(publisherList);
		  
		mockMvc.perform(MockMvcRequestBuilders.get("/publisher/state/{stateCode}",stateCode))
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$[0].stateCode").value(stateCode));
		verify(publisherService, times(1)).getAllPublisherByStateCode(stateCode);
		
	}
	

	@Test
	public void testSavePublisherInvalidInput() throws Exception
	{
		PublisherDto publisherDto = new PublisherDto();
	    publisherDto.setPublisherId(14);
	    publisherDto.setName(null); // Invalid null value for Name
	    publisherDto.setCity("Amravati");
	    publisherDto.setStateCode("MH");
	    ResponseDto responseDto = new ResponseDto();
	    responseDto.setResponseMessage("Error: Failed to save publisher");

	    // Validate the DTO using a Validator
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
	    Set<ConstraintViolation<PublisherDto>> violations = validator.validate(publisherDto);

	    // Expect a violation for the name field being null
	    assertThat(violations)
	            .extracting(ConstraintViolation::getPropertyPath)
	            .extracting(Object::toString)
	            .contains("name");

	    // Perform the test with the invalid DTO
	    mockMvc.perform(post("/publisher/post")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(publisherDto)))
	            .andExpect(status().isBadRequest());

	    verify(publisherService, times(0)).savePublisher(any(PublisherDto.class)); 
}
	@Test
	public void testGetAllPublishersInvalidId() throws Exception
	{
		List<PublisherDto> publisherDtos=Collections.emptyList();

		when(publisherService.getAllPublishers()).thenReturn(publisherDtos)	;
		mockMvc.perform(get("/publisher"))
				  .andExpect(status().isOk());

		verify(publisherService, times(1)).getAllPublishers();
	}
	@Test
	public void testGetPublisherByIdInvalidId() throws Exception
	{
		Integer publisherId=-1;
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setPublisherId(publisherId);
		when(publisherService.getPublisherById(publisherId))
		.thenThrow(new PublisherNotFoundException("No Publisher Found With id:" + publisherId));
		mockMvc.perform(get("/publisher/{publisherId}",publisherId))
		.andExpect(status().isNotFound());

		verify(publisherService, times(1)).getPublisherById(publisherId);
	}
	@Test
	public void testGetPublisherByNameInvalidName() throws Exception
	{
		String name="ok";
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setName(name);
		when(publisherService.getPublisherByName(name))
		.thenThrow(new PublisherNotFoundException("No Publisher Found With name:" + name));
		
		mockMvc.perform(get("/publisher/name/{name}",name))
		.andExpect(status().isNotFound());

		verify(publisherService, times(1)).getPublisherByName(name);
	}

	@Test
	public void testUpdateNameByPublisherIdInvalidId() throws Exception
	{
		Integer publisherId=-1;
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setPublisherId(publisherId);
		publisherDto.setName("CovertoCover Publishing");
		
		PublisherDto updatePublisherDto=new PublisherDto();
		updatePublisherDto.setPublisherId(publisherId);
		updatePublisherDto.setName("Aadesh");
		when(publisherService.updateNameByPublisherId(eq(publisherId), any(PublisherDto.class)))
		.thenThrow(new PublisherNotFoundException("Publisher not found with ID:" + publisherId));

		mockMvc.perform(put("/publisher/update/name/{publisherId}",publisherId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(publisherDto)))
				.andExpect(status().isNotFound());

		verify(publisherService, times(1)).updateNameByPublisherId(eq(publisherId), any(PublisherDto.class));

}

	@Test
	public void testUpdateCityByPublisherIdInvalidCity() throws Exception
	{
		Integer publisherId=-1;
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setPublisherId(publisherId);
		publisherDto.setCity("Rochester");
		
		PublisherDto updatePublisherDto=new PublisherDto();
		updatePublisherDto.setPublisherId(publisherId);
		updatePublisherDto.setCity("Amravati");
		when(publisherService.updateCityByPublisherId(eq(publisherId), any(PublisherDto.class)))
		.thenThrow(new PublisherNotFoundException("Publisher not found with ID:" + publisherId));

		mockMvc.perform(put("/publisher/update/city/{publisherId}",publisherId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(publisherDto)))
				.andExpect(status().isNotFound());

		verify(publisherService, times(1)).updateCityByPublisherId(eq(publisherId), any(PublisherDto.class));

}
	@Test
	public void testUpdateStateCodeByPublisherIdInvalidId() throws Exception
	{
		Integer publisherId=-1;
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setPublisherId(publisherId);
		publisherDto.setStateCode("NY");
		
		PublisherDto updatePublisherDto=new PublisherDto();
		updatePublisherDto.setPublisherId(publisherId);
		updatePublisherDto.setStateCode("MH");
		
		when(publisherService.updateStateCodeByPublisherId(eq(publisherId), any(PublisherDto.class)))
		.thenThrow(new PublisherNotFoundException("Publisher not found with ID:" + publisherId));

		mockMvc.perform(put("/publisher/update/state/{publisherId}",publisherId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(publisherDto)))
				.andExpect(status().isNotFound());

		verify(publisherService, times(1)).updateStateCodeByPublisherId(eq(publisherId), any(PublisherDto.class));

}
	@Test
	public void testGetAllPublishersByCityInvalidCity() throws Exception
	{
		String city="Rochester121";
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setCity(city);
		
		List<PublisherDto> publisherList=Collections.emptyList();
				when(publisherService.getAllPublisherByCity(city)).thenReturn(publisherList)	;
				mockMvc.perform(MockMvcRequestBuilders.get("/publisher/city/{city}",city))
				  .andExpect(status().isOk());

		verify(publisherService, times(1)).getAllPublisherByCity(city);
	}
	@Test
	public void testGetAllPublishersByStateCodeInvalidState() throws Exception
	{
		String stateCode="NY";
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setStateCode(stateCode);
		
		List<PublisherDto> publisherList=Collections.emptyList();
		when(publisherService.getAllPublisherByStateCode(stateCode)).thenReturn(publisherList);
		mockMvc.perform(MockMvcRequestBuilders.get("/publisher/state/{stateCode}",stateCode))
		  .andExpect(status().isOk());

		verify(publisherService, times(1)).getAllPublisherByStateCode(stateCode);
}
	}