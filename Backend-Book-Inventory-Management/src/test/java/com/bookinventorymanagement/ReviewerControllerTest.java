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

import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.dto.ReviewerDto;
import com.bookinventorymanagement.exceptions.ReviewerNotFoundException;
import com.bookinventorymanagement.service.ReviewerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ReviewerControllerTest {

	@MockBean
	private ReviewerService reviewerService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setupAuthentication() {
		Authentication auth = new UsernamePasswordAuthenticationToken("Aadesh_d", "123",
				Collections.singletonList(new SimpleGrantedAuthority("Admin")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	@Test
	void testSaveReviewer() throws Exception {

		ReviewerDto reviewerDto = new ReviewerDto();
		reviewerDto.setReviewerId(33);
		reviewerDto.setName("Aadesh Dakhode");
		reviewerDto.setEmployedBy("Capgemini");
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseMessage("reviewer added successfully");

		when(reviewerService.saveReviewer(reviewerDto)).thenReturn(responseDto);
		mockMvc.perform(
				post("/reviewer/post").contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewerDto)))
				.andExpect(status().isOk());

		verify(reviewerService, times(1)).saveReviewer(any(ReviewerDto.class));
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
	void testGetReviewerById() throws Exception {

		Integer reviewerId = 1;
		ReviewerDto reviewerDto = new ReviewerDto();
		reviewerDto.setReviewerId(reviewerId);

		when(reviewerService.getReviewerById(reviewerId)).thenReturn(reviewerDto);

		mockMvc.perform(get("/reviewer/{id}", reviewerId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.reviewerId", is(1)));

		verify(reviewerService, times(1)).getReviewerById(reviewerId);

	}

	@Test
	public void testUpdateReviewerById() throws Exception {
		Integer reviewerId = 1;
		ReviewerDto reviewerDto = new ReviewerDto();
		reviewerDto.setReviewerId(reviewerId);
		reviewerDto.setName("Jacobs");

		ReviewerDto updateReviewdto = new ReviewerDto();
		updateReviewdto.setReviewerId(reviewerId);
		updateReviewdto.setName("Taylor");

		when(reviewerService.updateReviewerById(eq(reviewerId), any(ReviewerDto.class))).thenReturn(updateReviewdto);
		mockMvc.perform(put("/reviewer/update/name/{id}", reviewerId).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(reviewerDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.reviewerId", is(1))).andExpect(jsonPath("$.name", is("Taylor")));

		verify(reviewerService, times(1)).updateReviewerById(eq(reviewerId), any(ReviewerDto.class));

	}

	@Test
	public void testUpdateEmployeeById() throws Exception {
		Integer reviewerId = 1;
		ReviewerDto reviewerDto = new ReviewerDto();
		reviewerDto.setReviewerId(reviewerId);
		reviewerDto.setEmployedBy("Gadget Boy");

		ReviewerDto updateReviewdto = new ReviewerDto();
		updateReviewdto.setReviewerId(reviewerId);
		updateReviewdto.setEmployedBy("Capgemini");

		when(reviewerService.updateEmployeeById(eq(reviewerId), any(ReviewerDto.class))).thenReturn(updateReviewdto);
		mockMvc.perform(put("/reviewer/update/employedby/{id}", reviewerId).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(reviewerDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.reviewerId", is(1))).andExpect(jsonPath("$.employedBy", is("Capgemini")));

		verify(reviewerService, times(1)).updateEmployeeById(eq(reviewerId), any(ReviewerDto.class));

	}

	@Test
	void testSaveReviewerInvalidInput() throws Exception {

		ReviewerDto reviewerDto = new ReviewerDto();
		reviewerDto.setReviewerId(33);
		reviewerDto.setName(null); // Invalid null value for name
		reviewerDto.setEmployedBy("Capgemini");

		// Validate the DTO using a Validator
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<ReviewerDto>> violations = validator.validate(reviewerDto);

		// Expect a violation for the name field being null
		assertThat(violations).extracting(ConstraintViolation::getPropertyPath).extracting(Object::toString)
				.contains("name");

		// Perform the test with the invalid DTO
		mockMvc.perform(
				post("/reviewer/post").contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewerDto)))
				.andExpect(status().isBadRequest());

		verify(reviewerService, times(0)).saveReviewer(any(ReviewerDto.class)); // Ensure saveReviewer is not called

	}

	@Test
	public void testGetReviewerByIdInvalidId() throws Exception {

		Integer reviewerId = 1;
		ReviewerDto reviewerDto = new ReviewerDto();
		reviewerDto.setReviewerId(reviewerId);

		when(reviewerService.getReviewerById(reviewerId))
				.thenThrow(new ReviewerNotFoundException("No Reviewer found by id:" + reviewerId));

		mockMvc.perform(get("/reviewer/{id}", reviewerId)).andExpect(status().isNotFound());

		verify(reviewerService, times(1)).getReviewerById(reviewerId);
	}

	@Test
	public void testUpdateReviewerByIdInvalidId() throws Exception {
		Integer reviewerId = -1;
		ReviewerDto reviewerDto = new ReviewerDto();
		reviewerDto.setReviewerId(reviewerId);
		reviewerDto.setName("Jacobs");

		ReviewerDto updateReviewdto = new ReviewerDto();
		updateReviewdto.setReviewerId(reviewerId);
		updateReviewdto.setName("Taylor");

		when(reviewerService.updateReviewerById(eq(reviewerId), any(ReviewerDto.class)))
				.thenThrow(new ReviewerNotFoundException("No Reviewer found by id:" + reviewerId));
		mockMvc.perform(put("/reviewer/update/name/{id}", reviewerId).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(reviewerDto))).andExpect(status().isNotFound());

		verify(reviewerService, times(1)).updateReviewerById(eq(reviewerId), any(ReviewerDto.class));
	}

	@Test
	public void testUpdateEmployeeByIdInvalidId() throws Exception {
		Integer reviewerId = -1;
		ReviewerDto reviewerDto = new ReviewerDto();
		reviewerDto.setReviewerId(reviewerId);
		reviewerDto.setEmployedBy("Gadget Boy");

		ReviewerDto updateReviewdto = new ReviewerDto();
		updateReviewdto.setReviewerId(reviewerId);
		updateReviewdto.setEmployedBy("Capgemini");

		when(reviewerService.updateEmployeeById(eq(reviewerId), any(ReviewerDto.class)))
				.thenThrow(new ReviewerNotFoundException("No Reviewer found by id:" + reviewerId));
		mockMvc.perform(put("/reviewer/update/employedby/{id}", reviewerId).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(reviewerDto))).andExpect(status().isNotFound());
		verify(reviewerService, times(1)).updateEmployeeById(eq(reviewerId), any(ReviewerDto.class));
	}
}
