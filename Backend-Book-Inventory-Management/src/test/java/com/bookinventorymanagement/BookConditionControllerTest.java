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

import java.math.BigDecimal;
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

import com.bookinventorymanagement.dto.BookConditionDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.exceptions.BookConditionNotFoundException;
import com.bookinventorymanagement.service.BookConditionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookConditionControllerTest {

	@MockBean
	private BookConditionService bookConditionService;

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setupAuthentication() {
		Authentication auth = new UsernamePasswordAuthenticationToken("Aadesh_d", "123",
				Collections.singletonList(new SimpleGrantedAuthority("Admin")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}


	@Test
	public void testSaveCondition() throws Exception {
		BookConditionDto bookConditionDto = new BookConditionDto();
		bookConditionDto.setRanks(9);
		bookConditionDto.setDescription("Good");
		bookConditionDto.setFullDescription("No pages missing");
		bookConditionDto.setPrice(new BigDecimal(30.00));
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseMessage("Book condition added successfully");

		when(bookConditionService.saveBookCondition(bookConditionDto)).thenReturn(responseDto);
		mockMvc.perform(post("/bookcondition/post").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookConditionDto))).andExpect(status().isOk());

		verify(bookConditionService, times(1)).saveBookCondition(any(BookConditionDto.class));

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
	public void testGetBookConditionById() throws Exception {
		Integer ranks = 1;
		BookConditionDto bookConditionDto = new BookConditionDto();
		bookConditionDto.setRanks(ranks);

		when(bookConditionService.getBookConditionById(ranks)).thenReturn(bookConditionDto);
		mockMvc.perform(get("/bookcondition/{ranks}", ranks)).andExpect(status().isOk())
				.andExpect(jsonPath("$.ranks", is(1)));

		verify(bookConditionService, times(1)).getBookConditionById(ranks);
	}

	@Test
	public void testUpdateDescriptionById() throws Exception {
		Integer ranks = 1;
		BookConditionDto bookConditionDto = new BookConditionDto();
		bookConditionDto.setRanks(ranks);
		bookConditionDto.setDescription("Bad");

		BookConditionDto updateConditionDto = new BookConditionDto();
		updateConditionDto.setRanks(ranks);
		updateConditionDto.setDescription("Good");
		when(bookConditionService.updateDescriptionById(eq(ranks), any(BookConditionDto.class)))
				.thenReturn(updateConditionDto);
		mockMvc.perform(put("/bookcondition/update/description/{ranks}", ranks).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookConditionDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.ranks", is(1))).andExpect(jsonPath("$.description", is("Good")));

		verify(bookConditionService, times(1)).updateDescriptionById(eq(ranks), any(BookConditionDto.class));

	}

	@Test
	public void testUpdateFullDescriptionById() throws Exception {
		Integer ranks = 1;
		BookConditionDto bookConditionDto = new BookConditionDto();
		bookConditionDto.setRanks(ranks);
		bookConditionDto.setFullDescription("Not much is left of the book. Parts are not legible, cover may be missing.");

		BookConditionDto updateConditionDto = new BookConditionDto();
		updateConditionDto.setRanks(ranks);
		updateConditionDto.setFullDescription("New Book");
		when(bookConditionService.updateFullDescriptionById(eq(ranks), any(BookConditionDto.class)))
				.thenReturn(updateConditionDto);
		mockMvc.perform(put("/bookcondition/update/fulldescription/{ranks}", ranks)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(bookConditionDto)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.ranks", is(1)))
				.andExpect(jsonPath("$.fullDescription", is("New Book")));

		verify(bookConditionService, times(1)).updateFullDescriptionById(eq(ranks), any(BookConditionDto.class));

	}

	@Test
	public void testUpdatepriceById() throws Exception {
		Integer ranks = 1;
		BookConditionDto bookConditionDto = new BookConditionDto();
		bookConditionDto.setRanks(ranks);
		bookConditionDto.setPrice(new BigDecimal(5.00));

		BookConditionDto updateConditionDto = new BookConditionDto();
		updateConditionDto.setRanks(ranks);
		updateConditionDto.setPrice(new BigDecimal(10.00));

		when(bookConditionService.updatePriceById(eq(ranks), any(BookConditionDto.class)))
				.thenReturn(updateConditionDto);
		mockMvc.perform(put("/bookcondition/update/price/{ranks}", ranks).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookConditionDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.ranks").value(1)).andExpect(jsonPath("$.price").value(10.00));

		verify(bookConditionService, times(1)).updatePriceById(eq(ranks), any(BookConditionDto.class));

	}

	@Test
	public void testSaveConditionInvalidInput() throws Exception {

		BookConditionDto bookConditionDto = new BookConditionDto();
	    bookConditionDto.setRanks(9);
	    bookConditionDto.setDescription(null); // Invalid null value for description
	    bookConditionDto.setFullDescription("No pages missing");
	    bookConditionDto.setPrice(new BigDecimal(30.00));
	 
	    // Validate the DTO using a Validator
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
	    Set<ConstraintViolation<BookConditionDto>> violations = validator.validate(bookConditionDto);
	 
	    // Expect a validation error
	    assertThat(violations)
	    .extracting(ConstraintViolation::getPropertyPath)
        .extracting(Object::toString)
        .contains("description");
	 
	    // Perform the test with the invalid DTO
	    mockMvc.perform(post("/bookcondition/post")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(bookConditionDto)))
	            .andExpect(status().isBadRequest());
	 
	    verify(bookConditionService, times(0)).saveBookCondition(any(BookConditionDto.class)); // Ensure saveBookCondition is not called

	}

	@Test

	public void testGetBookConditionByIdNotFound() throws Exception {

		Integer ranks = 1;
		BookConditionDto bookConditionDto = new BookConditionDto();
		bookConditionDto.setRanks(ranks);

		when(bookConditionService.getBookConditionById(ranks))
				.thenThrow(new BookConditionNotFoundException("No Book Condition Found!"));
		mockMvc.perform(get("/bookcondition/{ranks}", ranks)).andExpect(status().isNotFound());

		verify(bookConditionService, times(1)).getBookConditionById(ranks);

	}

	@Test
	public void testUpdateDescriptionByIdInvalidId() throws Exception {
		Integer ranks = -1;
		BookConditionDto bookConditionDto = new BookConditionDto();
		bookConditionDto.setRanks(ranks);
		bookConditionDto.setDescription("Bad");

		BookConditionDto updateConditionDto = new BookConditionDto();
		updateConditionDto.setRanks(ranks);
		updateConditionDto.setDescription("Good");
		when(bookConditionService.updateDescriptionById(eq(ranks), any(BookConditionDto.class)))
				.thenThrow(new BookConditionNotFoundException("Book Description not found with Rank: -1"));

		mockMvc.perform(put("/bookcondition/update/description/{ranks}", ranks).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookConditionDto))).andExpect(status().isNotFound());

		verify(bookConditionService, times(1)).updateDescriptionById(eq(ranks), any(BookConditionDto.class));
	}

	@Test
	public void testUpdatepriceByIdInvalidId() throws Exception {
		Integer ranks = -1;
		BookConditionDto bookConditionDto = new BookConditionDto();
		bookConditionDto.setRanks(ranks);
		bookConditionDto
				.setFullDescription("Not much is left of the book. Parts are not legible, cover may be missing.");

		BookConditionDto updateConditionDto = new BookConditionDto();
		updateConditionDto.setRanks(ranks);
		updateConditionDto.setFullDescription("New Book");

		when(bookConditionService.updateFullDescriptionById(eq(ranks), any(BookConditionDto.class)))
				.thenThrow(new BookConditionNotFoundException("Book FullDescription not found with Rank: -1"));

		mockMvc.perform(put("/bookcondition/update/fulldescription/{ranks}", ranks)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(bookConditionDto)))
				.andExpect(status().isNotFound());

		verify(bookConditionService, times(1)).updateFullDescriptionById(eq(ranks), any(BookConditionDto.class));
	}

	@Test
	public void testUpdateFullDescriptionByIdInvalidId() throws Exception {
		Integer ranks = -1;
		BookConditionDto bookConditionDto = new BookConditionDto();
		bookConditionDto.setRanks(ranks);
		bookConditionDto.setPrice(new BigDecimal(5.00));

		BookConditionDto updateConditionDto = new BookConditionDto();
		updateConditionDto.setRanks(ranks);
		updateConditionDto.setPrice(new BigDecimal(10.00));

		when(bookConditionService.updatePriceById(eq(ranks), any(BookConditionDto.class)))
				.thenThrow(new BookConditionNotFoundException("Book price not found with Rank: -1"));

		mockMvc.perform(put("/bookcondition/update/price/{ranks}", ranks).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookConditionDto))).andExpect(status().isNotFound());

		verify(bookConditionService, times(1)).updatePriceById(eq(ranks), any(BookConditionDto.class));
	}
}
