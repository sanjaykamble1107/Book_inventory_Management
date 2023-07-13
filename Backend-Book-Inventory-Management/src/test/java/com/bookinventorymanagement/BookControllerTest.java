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

import com.bookinventorymanagement.dto.BookDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.exceptions.AuthorNotFoundException;
import com.bookinventorymanagement.exceptions.BookNotFoundException;
import com.bookinventorymanagement.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

class BookControllerTest {

	@MockBean
	private BookService bookService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setupAuthentication() {
		Authentication auth = new UsernamePasswordAuthenticationToken("Aadesh_d", "123",
				Collections.singletonList(new SimpleGrantedAuthority("Admin")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Test
	public void testGetAllBooks() throws Exception {
		BookDto bookDto = new BookDto();
		List<BookDto> bookList = Arrays.asList(bookDto);
		when(bookService.getAllBooks()).thenReturn(bookList);

		mockMvc.perform(MockMvcRequestBuilders.get("/book"))

				.andExpect(status().isOk());
		verify(bookService, times(1)).getAllBooks();
	}

	@Test
	public void testSaveBook() throws Exception {
		BookDto bookDto = new BookDto();
		bookDto.setIsbn("1-111-11111-1");
		bookDto.setTitle("Java Book");
		bookDto.setDescription("James Gosling");
		bookDto.setCategory(2);
		bookDto.setEdition("5");
		bookDto.setPublisherId(15);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseMessage("Book added successfully");

		when(bookService.saveBook(bookDto)).thenReturn(responseDto);

		mockMvc.perform(post("/book/post").contentType(MediaType.APPLICATION_JSON).content(asJsonString(bookDto)))
				.andExpect(status().isOk());

		verify(bookService, times(1)).saveBook(any(BookDto.class));

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
	public void testGetBookByIsbn() throws Exception {
		String isbn = "1-111-11111-4";
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);

		when(bookService.getBookByIsbn(isbn)).thenReturn(bookDto);
		mockMvc.perform(get("/book/{isbn}", isbn)).andExpect(status().isOk())
				.andExpect(jsonPath("$.isbn", is("1-111-11111-4")));

		verify(bookService, times(1)).getBookByIsbn(isbn);
	}

	@Test
	public void testGetBookByTitle() throws Exception {
		String title = "Women are From Venus ORACLE is from Beyond Pluto";
		BookDto bookDto = new BookDto();
		bookDto.setTitle(title);

		when(bookService.getBookByTitle(title)).thenReturn(bookDto);
		mockMvc.perform(get("/book/title/{title}", title)).andExpect(status().isOk())
				.andExpect(jsonPath("$.title", is("Women are From Venus ORACLE is from Beyond Pluto")));

		verify(bookService, times(1)).getBookByTitle(title);

	}

	@Test
	public void testUpdateTitleByIsbn() throws Exception {
		String isbn = "1-111-11111-4";
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);
		bookDto.setTitle("Women are From Venus ORACLE is from Beyond Pluto");

		BookDto updateBookDto = new BookDto();
		updateBookDto.setIsbn(isbn);
		updateBookDto.setTitle("Java Book");

		when(bookService.updateTitleByIsbn(eq(isbn), any(BookDto.class))).thenReturn(updateBookDto);
		mockMvc.perform(put("/book/update/title/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.title", is("Java Book")));

		verify(bookService, times(1)).updateTitleByIsbn(eq(isbn), any(BookDto.class));

	}

	@Test
	public void testGetBookByPublisherId() throws Exception {
		Integer publisherId = 1;
		BookDto bookDto = new BookDto();
		bookDto.setPublisherId(publisherId);
		List<BookDto> bookList = Arrays.asList(bookDto);
		when(bookService.getBookByPublisherId(publisherId)).thenReturn(bookList);

		mockMvc.perform(MockMvcRequestBuilders.get("/book/publisher/{publisherId}", publisherId))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].publisherId").value(publisherId));
		verify(bookService, times(1)).getBookByPublisherId(publisherId);

	}

	@Test
	public void testGetBookByCategory() throws Exception {
		Integer catId = 1;
		BookDto bookDto = new BookDto();
		bookDto.setCategory(catId);
		List<BookDto> bookList = Arrays.asList(bookDto);
		when(bookService.getBookByCategory(catId)).thenReturn(bookList);

		mockMvc.perform(MockMvcRequestBuilders.get("/book/category/{category}", catId)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].category").value(catId));
		verify(bookService, times(1)).getBookByCategory(catId);

	}

	@Test
	public void testUpdateDescriptionByIsbn() throws Exception {
		String isbn = "1-111-11111-4";
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);
		bookDto.setDescription("New York Times Best Seller 27 weeks");

		BookDto updateBookDto = new BookDto();
		updateBookDto.setIsbn(isbn);
		updateBookDto.setDescription("Java Programming");

		when(bookService.updateDescriptionByIsbn(eq(isbn), any(BookDto.class))).thenReturn(updateBookDto);
		mockMvc.perform(put("/book/update/description/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.description", is("Java Programming")));

		verify(bookService, times(1)).updateDescriptionByIsbn(eq(isbn), any(BookDto.class));
	}

	@Test
	public void testUpdateEditionByIsbn() throws Exception {
		String isbn = "1-111-11111-4";
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);
		bookDto.setEdition("4");

		BookDto updateBookDto = new BookDto();
		updateBookDto.setIsbn(isbn);
		updateBookDto.setEdition("5");

		when(bookService.updateEditionByIsbn(eq(isbn), any(BookDto.class))).thenReturn(updateBookDto);

		mockMvc.perform(put("/book/update/edition/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookDto))).andExpect(status().isOk()).andExpect(jsonPath("$.edition", is("5")));

		verify(bookService, times(1)).updateEditionByIsbn(eq(isbn), any(BookDto.class));

	}

	@Test
	public void testUpdateCategoryByIsbn() throws Exception {
		String isbn = "1-111-11111-4";
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);
		bookDto.setCategory(1);

		BookDto updateBookDto = new BookDto();
		updateBookDto.setIsbn(isbn);
		updateBookDto.setCategory(5);

		when(bookService.updateCategoryByIsbn(eq(isbn), any(BookDto.class))).thenReturn(updateBookDto);
		mockMvc.perform(put("/book/update/category/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookDto))).andExpect(status().isOk()).andExpect(jsonPath("$.category", is(5)));

		verify(bookService, times(1)).updateCategoryByIsbn(eq(isbn), any(BookDto.class));

	}

	@Test
	public void testUpdatePublisherIdByIsbn() throws Exception {
		String isbn = "1-111-11111-4";
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);
		bookDto.setPublisherId(1);

		BookDto updateBookDto = new BookDto();
		updateBookDto.setIsbn(isbn);
		updateBookDto.setPublisherId(2);

		when(bookService.upatePublisherIdByIsbn(eq(isbn), any(BookDto.class))).thenReturn(updateBookDto);
		mockMvc.perform(put("/book/update/publisher/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookDto))).andExpect(status().isOk()).andExpect(jsonPath("$.publisherId", is(2)));

		verify(bookService, times(1)).upatePublisherIdByIsbn(eq(isbn), any(BookDto.class));

	}

	@Test
	public void testSaveBookInvalidInput() throws Exception {
		BookDto bookDto = new BookDto();
		bookDto.setIsbn("1-111-11111-1");
		bookDto.setTitle("Java Book");
		bookDto.setDescription(null); // Invalid null value for description
		bookDto.setCategory(2);
		bookDto.setEdition("5");
		bookDto.setPublisherId(15);

		// Validate the DTO using a Validator
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<BookDto>> violations = validator.validate(bookDto);

		// Expect a validation error for the "description" property
		assertThat(violations).extracting(violation -> violation.getPropertyPath().toString()).contains("description");

		// Perform the test with the invalid DTO
		mockMvc.perform(post("/book/post").contentType(MediaType.APPLICATION_JSON).content(asJsonString(bookDto)))
				.andExpect(status().isBadRequest());

		verify(bookService, times(0)).saveBook(any(BookDto.class));
		// Ensure saveBook is not called
	}

	@Test
	public void testGetAllBooksNoBooksFound() throws Exception {
		List<BookDto> emptyBookList = Collections.emptyList();
		when(bookService.getAllBooks()).thenReturn(emptyBookList);

		mockMvc.perform(MockMvcRequestBuilders.get("/book")).andExpect(status().isOk());

		verify(bookService, times(1)).getAllBooks();
	}

	@Test
	public void testGetBookByIsbnInvalidId() throws Exception {
		String isbn = "1-111-11111-4-z";
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);

		when(bookService.getBookByIsbn(isbn)).thenThrow(new BookNotFoundException("No Book Found With id:" + isbn));
		mockMvc.perform(get("/book/{isbn}", isbn)).andExpect(status().isNotFound());

		verify(bookService, times(1)).getBookByIsbn(isbn);
	}

	@Test
	public void testGetBookByTitleInvalidTitle() throws Exception {
		String title = "Women";
		BookDto bookDto = new BookDto();
		bookDto.setTitle(title);

		when(bookService.getBookByTitle(title)).thenThrow(new BookNotFoundException("No Book Found By Title" + title));
		mockMvc.perform(get("/book/title/{title}", title)).andExpect(status().isNotFound());

		verify(bookService, times(1)).getBookByTitle(title);
	}

	@Test
	public void testUpdateTitleByIsbnInvalidId() throws Exception {
		String isbn = "1-111-11111-4-z";
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);
		bookDto.setTitle("Women are From Venus ORACLE is from Beyond Pluto");

		BookDto updateBookDto = new BookDto();
		updateBookDto.setIsbn(isbn);
		updateBookDto.setTitle("Java Book");

		when(bookService.updateTitleByIsbn(eq(isbn), any(BookDto.class)))
				.thenThrow(new AuthorNotFoundException("Book not found with ID:" + isbn));

		mockMvc.perform(put("/book/update/title/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookDto))).andExpect(status().isNotFound());

		verify(bookService, times(1)).updateTitleByIsbn(eq(isbn), any(BookDto.class));
	}

	@Test
	public void testGetBookByPublisherIdInvalidId() throws Exception {
		Integer publisherId = -1;
		BookDto bookDto = new BookDto();
		bookDto.setPublisherId(publisherId);
		when(bookService.getBookByPublisherId(publisherId))
				.thenThrow(new BookNotFoundException("Book not found with ID:" + publisherId));

		mockMvc.perform(get("/book/publisher/{publisherId}", publisherId)).andExpect(status().isNotFound());

		verify(bookService, times(1)).getBookByPublisherId(publisherId);
	}

	@Test
	public void testGetBookByCategoryInvalidId() throws Exception {
		Integer catId = -1;
		BookDto bookDto = new BookDto();
		bookDto.setCategory(catId);
		when(bookService.getBookByCategory(catId))
				.thenThrow(new BookNotFoundException("Book not found with ID:" + catId));

		mockMvc.perform(MockMvcRequestBuilders.get("/book/category/{category}", catId))
				.andExpect(status().isNotFound());

		verify(bookService, times(1)).getBookByCategory(catId);
	}

	@Test
	public void testUpdateDescriptionByIsbnInvalidId() throws Exception {
		String isbn = "1-111-11111-4-z";
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);
		bookDto.setDescription("New York Times Best Seller 27 weeks");

		BookDto updateBookDto = new BookDto();
		updateBookDto.setIsbn(isbn);
		updateBookDto.setDescription("Java Programming");
		when(bookService.updateDescriptionByIsbn(eq(isbn), any(BookDto.class)))
				.thenThrow(new AuthorNotFoundException("Book not found with ID:" + isbn));

		mockMvc.perform(put("/book/update/description/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookDto))).andExpect(status().isNotFound());

		verify(bookService, times(1)).updateDescriptionByIsbn(eq(isbn), any(BookDto.class));
	}

	@Test
	public void testUpdateEditionByIsbnInvalidId() throws Exception {
		String isbn = "1-111-11111-4-z";
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);
		bookDto.setEdition("4");

		BookDto updateBookDto = new BookDto();
		updateBookDto.setIsbn(isbn);
		updateBookDto.setEdition("5");

		when(bookService.updateEditionByIsbn(eq(isbn), any(BookDto.class)))
				.thenThrow(new AuthorNotFoundException("Book not found with ID:" + isbn));

		mockMvc.perform(put("/book/update/edition/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookDto))).andExpect(status().isNotFound());

		verify(bookService, times(1)).updateEditionByIsbn(eq(isbn), any(BookDto.class));
	}

	@Test
	public void testUpdateCategoryByIsbnInvalidId() throws Exception {
		String isbn = "1-111-11111-4-z";
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);
		bookDto.setCategory(1);

		BookDto updateBookDto = new BookDto();
		updateBookDto.setIsbn(isbn);
		updateBookDto.setCategory(5);
		when(bookService.updateCategoryByIsbn(eq(isbn), any(BookDto.class)))
				.thenThrow(new AuthorNotFoundException("Book not found with ID:" + isbn));

		mockMvc.perform(put("/book/update/category/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookDto))).andExpect(status().isNotFound());

		verify(bookService, times(1)).updateCategoryByIsbn(eq(isbn), any(BookDto.class));
	}

	@Test
	public void testUpdatePublisherIdByIsbnInvalidId() throws Exception {
		String isbn = "1-111-11111-4-z";
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);
		bookDto.setPublisherId(1);

		BookDto updateBookDto = new BookDto();
		updateBookDto.setIsbn(isbn);
		updateBookDto.setPublisherId(2);
		when(bookService.upatePublisherIdByIsbn(eq(isbn), any(BookDto.class)))
				.thenThrow(new AuthorNotFoundException("Book not found with ID:" + isbn));

		mockMvc.perform(put("/book/update/publisher/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookDto))).andExpect(status().isNotFound());

		verify(bookService, times(1)).upatePublisherIdByIsbn(eq(isbn), any(BookDto.class));
	}

}