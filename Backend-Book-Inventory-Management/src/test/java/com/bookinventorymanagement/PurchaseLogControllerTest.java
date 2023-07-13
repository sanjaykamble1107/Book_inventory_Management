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

import com.bookinventorymanagement.dto.PurchaseLogDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.exceptions.PurchaseLogNotFoundException;
import com.bookinventorymanagement.service.PurchaseLogService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PurchaseLogControllerTest {

	@MockBean
	private PurchaseLogService logService;

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setupAuthentication() {
		Authentication auth = new UsernamePasswordAuthenticationToken("Aadesh_d", "123",
				Collections.singletonList(new SimpleGrantedAuthority("Admin")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Test
	public void testSavePurchaseLog() throws Exception {
		PurchaseLogDto logDto = new PurchaseLogDto();
		logDto.setUserId(1000000);
		logDto.setInventoryId(1000065);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseMessage("Purchase Log added successfully");

		when(logService.savePurchaseLog(logDto)).thenReturn(responseDto);
		mockMvc.perform(post("/purchaselog/post").contentType(MediaType.APPLICATION_JSON).content(asJsonString(logDto)))
				.andExpect(status().isOk());

		verify(logService, times(1)).savePurchaseLog(logDto);
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
	public void testGetPurchaLogByUserId() throws Exception {
		Integer userId = 1000000;
		PurchaseLogDto logDto = new PurchaseLogDto();
		logDto.setUserId(userId);
		when(logService.getPurchaseLogByUserId(userId)).thenReturn(logDto);
		mockMvc.perform(get("/purchaselog/{userId}", userId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.userId").value(1000000));

		verify(logService, times(1)).getPurchaseLogByUserId(userId);
	}

	@Test
	public void testUpdateInventoryIdByUserId() throws Exception {
		Integer userId = 1000000;
		PurchaseLogDto logDto = new PurchaseLogDto();
		logDto.setUserId(userId);
		logDto.setInventoryId(1000065);

		PurchaseLogDto updateLogDto = new PurchaseLogDto();
		updateLogDto.setUserId(userId);
		updateLogDto.setInventoryId(1000066);

		when(logService.updateInventoryIdByUserId(eq(userId), any(PurchaseLogDto.class))).thenReturn(updateLogDto);
		mockMvc.perform(put("/purchaselog/update/inventoryId/{userId}", userId).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(logDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.userId").value(1000000)).andExpect(jsonPath("$.inventoryId").value(1000066));

		verify(logService, times(1)).updateInventoryIdByUserId(eq(userId), any(PurchaseLogDto.class));
	}

	@Test
	public void testSavePurchaseLogInvalidInput() throws Exception {
		PurchaseLogDto logDto = new PurchaseLogDto();
		logDto.setUserId(1000000);
		logDto.setInventoryId(null); // Invalid value for inventoryId
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseMessage("Error: Failed to add purchase log");

		// Validate the DTO using a Validator
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<PurchaseLogDto>> violations = validator.validate(logDto);

		// Expect a violation for the inventoryId field being invalid
		assertThat(violations).extracting(ConstraintViolation::getPropertyPath).extracting(Object::toString)
				.contains("inventoryId");

		// Perform the test with the invalid DTO
		mockMvc.perform(post("/purchaselog/post").contentType(MediaType.APPLICATION_JSON).content(asJsonString(logDto)))
				.andExpect(status().isBadRequest());

		verify(logService, times(0)).savePurchaseLog(any(PurchaseLogDto.class)); // Ensure savePurchaseLog is not called

	}

	@Test
	public void testGetPurchaLogByUserIdInvalidId() throws Exception {
		Integer userId = -1000000;
		PurchaseLogDto logDto = new PurchaseLogDto();
		logDto.setUserId(userId);
		when(logService.getPurchaseLogByUserId(userId))
				.thenThrow(new PurchaseLogNotFoundException("No pUrchaseLog Found By id:" + userId));
		mockMvc.perform(get("/purchaselog/{userId}", userId)).andExpect(status().isNotFound());

		verify(logService, times(1)).getPurchaseLogByUserId(userId);
	}

	@Test
	public void testUpdateInventoryIdByUserIdInvalidId() throws Exception {
		Integer userId = -1000000;
		PurchaseLogDto logDto = new PurchaseLogDto();
		logDto.setUserId(userId);
		logDto.setInventoryId(1000065);

		PurchaseLogDto updateLogDto = new PurchaseLogDto();
		updateLogDto.setUserId(userId);
		updateLogDto.setInventoryId(1000066);

		when(logService.updateInventoryIdByUserId(eq(userId), any(PurchaseLogDto.class)))
				.thenThrow(new PurchaseLogNotFoundException("No pUrchaseLog Found By id:" + userId));
		mockMvc.perform(put("/purchaselog/update/inventoryId/{userId}", userId).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(logDto))).andExpect(status().isNotFound());

		verify(logService, times(1)).updateInventoryIdByUserId(eq(userId), any(PurchaseLogDto.class));
	}
}
