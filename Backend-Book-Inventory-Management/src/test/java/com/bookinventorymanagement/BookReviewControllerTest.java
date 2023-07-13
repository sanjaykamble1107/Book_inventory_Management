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

import com.bookinventorymanagement.dto.BookReviewDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.exceptions.BookNotFoundException;
import com.bookinventorymanagement.exceptions.BookReviewNotFoundException;
import com.bookinventorymanagement.service.BookReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookReviewControllerTest {

	@MockBean
	private BookReviewService bookReviewService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setupAuthentication() {
		Authentication auth = new UsernamePasswordAuthenticationToken("Aadesh_d", "123",
				Collections.singletonList(new SimpleGrantedAuthority("Admin")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	@Test
	void testSaveBookReview() throws Exception {
		// Prepare
		BookReviewDto bookReviewDto = new BookReviewDto();
		bookReviewDto.setIsbn("1-111-11111-1");
		bookReviewDto.setReviewerID(20);
		bookReviewDto.setRating(10);
		bookReviewDto.setComments("Java Project");
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseMessage("Book review added successfully");

		when(bookReviewService.saveBookReview(bookReviewDto)).thenReturn(responseDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/bookreview/post").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookReviewDto))).andExpect(MockMvcResultMatchers.status().isOk());

		verify(bookReviewService, times(1)).saveBookReview(any(BookReviewDto.class));
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
	public void testGetBookReviewByIsbn() throws Exception {
		String isbn = "1-111-11111-4";
		BookReviewDto bookReviewDto = new BookReviewDto();
		bookReviewDto.setIsbn(isbn);

		List<BookReviewDto> bookReviewList = Arrays.asList(bookReviewDto);
		when(bookReviewService.getBookReviewByIsbn(isbn)).thenReturn(bookReviewList);

		mockMvc.perform(get("/bookreview/{isbn}", isbn)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].isbn", is("1-111-11111-4")));

		verify(bookReviewService, times(1)).getBookReviewByIsbn(isbn);

	}

	@Test
	public void testUpdateRatingByIsbn() throws Exception {
		String isbn = "1-111-11111-4";
		BookReviewDto bookReviewDto = new BookReviewDto();
		bookReviewDto.setIsbn(isbn);
		bookReviewDto.setRating(10);

		BookReviewDto updateBookReviewDto = new BookReviewDto();
		updateBookReviewDto.setIsbn(isbn);
		updateBookReviewDto.setRating(11);

		List<BookReviewDto> bookList = Arrays.asList(updateBookReviewDto);
		when(bookReviewService.updateRatingByIsbn(eq(isbn), any(BookReviewDto.class))).thenReturn(bookList);
		mockMvc.perform(put("/bookreview/update/rating/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookReviewDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].isbn").value("1-111-11111-4")).andExpect(jsonPath("$[0].rating").value(11));

		verify(bookReviewService, times(1)).updateRatingByIsbn(eq(isbn), any(BookReviewDto.class));
	}

	@Test
	public void testUpdateCommentByIsbn() throws Exception {
		String isbn = "1-111-11111-4";
		BookReviewDto bookReviewDto = new BookReviewDto();
		bookReviewDto.setIsbn(isbn);
		bookReviewDto.setComments("Java Project");

		BookReviewDto updateBookReviewDto = new BookReviewDto();
		updateBookReviewDto.setIsbn(isbn);
		updateBookReviewDto.setComments("Python Project");
		List<BookReviewDto> bookList = Arrays.asList(updateBookReviewDto);

		when(bookReviewService.updateCommentsByIsbn(eq(isbn), any(BookReviewDto.class))).thenReturn(bookList);
		mockMvc.perform(put("/bookreview/update/comments/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookReviewDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].isbn").value("1-111-11111-4"))
				.andExpect(jsonPath("$[0].comments", is("Python Project")));

		verify(bookReviewService, times(1)).updateCommentsByIsbn(eq(isbn), any(BookReviewDto.class));
	}

	@Test
	void testSaveBookReviewInvalidInput() throws Exception {
		  BookReviewDto bookReviewDto = new BookReviewDto();
		    bookReviewDto.setIsbn("1-111-11111-1");
		    bookReviewDto.setReviewerID(20);
		    bookReviewDto.setRating(null); // Invalid rating value
		    bookReviewDto.setComments("Java Project");

		    // Validate the DTO using a Validator
		    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		    Validator validator = factory.getValidator();
		    Set<ConstraintViolation<BookReviewDto>> violations = validator.validate(bookReviewDto);

		    // Expect a validation error
		    assertThat(violations)
		    .extracting(ConstraintViolation::getPropertyPath)
            .extracting(Object::toString)
            .contains("rating");

		    // Perform the test with the invalid DTO
		    mockMvc.perform(post("/bookreview/post")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(asJsonString(bookReviewDto)))
		            .andExpect(status().isBadRequest());

		    verify(bookReviewService, times(0)).saveBookReview(any(BookReviewDto.class)); // Ensure saveBookReview is not called
	}
	@Test
	public void testGetBookReviewByIsbnInvalidId() throws Exception {
		String isbn = "1-111-11111-4-z";
		BookReviewDto bookReviewDto = new BookReviewDto();
		bookReviewDto.setIsbn(isbn);
		when(bookReviewService.getBookReviewByIsbn(isbn))
				.thenThrow(new BookNotFoundException("No Book Review Found With id:" + isbn));
		mockMvc.perform(get("/bookreview/{isbn}", isbn)).andExpect(status().isNotFound());

		verify(bookReviewService, times(1)).getBookReviewByIsbn(isbn);
	}

	@Test
	public void testUpdateRatingByIsbnInvalidId() throws Exception {
		String isbn = "1-111-11111-4-z";
		BookReviewDto bookReviewDto = new BookReviewDto();
		bookReviewDto.setIsbn(isbn);
		bookReviewDto.setRating(10);

		BookReviewDto updateBookReviewDto = new BookReviewDto();
		updateBookReviewDto.setIsbn(isbn);
		updateBookReviewDto.setRating(11);
		when(bookReviewService.updateRatingByIsbn(eq(isbn), any(BookReviewDto.class)))
				.thenThrow(new BookReviewNotFoundException("Book Review not found with isbn:" + isbn));

		mockMvc.perform(put("/bookreview/update/rating/{isbn}", isbn)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookReviewDto)))
		.andExpect(status().isNotFound());

		verify(bookReviewService, times(1)).updateRatingByIsbn(eq(isbn), any(BookReviewDto.class));
	}
	
	@Test
	public void testUpdateCommentByIsbnInvalidId() throws Exception {
		String isbn = "1-111-11111-4-x";
		BookReviewDto bookReviewDto = new BookReviewDto();
		bookReviewDto.setIsbn(isbn);
		bookReviewDto.setComments("Java Project");

		BookReviewDto updateBookReviewDto = new BookReviewDto();
		updateBookReviewDto.setIsbn(isbn);
		updateBookReviewDto.setComments("Python Project");
		when(bookReviewService.updateCommentsByIsbn(eq(isbn), any(BookReviewDto.class)))
		.thenThrow(new BookReviewNotFoundException("Book Review not found with isbn:" + isbn));

		mockMvc.perform(put("/bookreview/update/comments/{isbn}", isbn)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookReviewDto)))
				.andExpect(status().isNotFound());

		verify(bookReviewService, times(1)).updateCommentsByIsbn(eq(isbn), any(BookReviewDto.class));
}

}
