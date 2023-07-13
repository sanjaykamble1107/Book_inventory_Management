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
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.bookinventorymanagement.dto.AuthorDto;
import com.bookinventorymanagement.dto.BookDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.exceptions.AuthorNotFoundException;
import com.bookinventorymanagement.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@ExtendWith(SpringExtension.class) 
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthorService authorService;
	
	

	@BeforeEach
	public void setupAuthentication() {
		Authentication auth = new UsernamePasswordAuthenticationToken("Aadesh", "123",
				Collections.singletonList(new SimpleGrantedAuthority("Admin")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Test
	
	public void testAddAuthor() throws Exception {
		
		AuthorDto authorDto = new AuthorDto();
		authorDto.setAuthorId(28);
		authorDto.setFirstName("Aadesh");
		authorDto.setLastName("Dakhode");
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseMessage("Author added successfully");

		when(authorService.saveAuthor(authorDto)).thenReturn(responseDto);

		mockMvc.perform(post("/author/post")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(authorDto)))
		.andExpect(status().isOk());

		verify(authorService, times(1)).saveAuthor(authorDto);
	}


	@Test
	public void testGetAuthorById() throws Exception {
		int authorId = 1;

		AuthorDto authorDto = new AuthorDto();
		authorDto.setAuthorId(authorId);

		when(authorService.getAuthorById(authorId)).thenReturn(authorDto);

		mockMvc.perform(get("/author/{authorId}", authorId).header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY4OTA5NzU3OCwiZXhwIjoxNjg5MTE1NTc4fQ.PdpOCqc_TpVz3L8IXePTaw7GicyIkLmesjkslPKtzyE")).andExpect(status().isOk())
				.andExpect(jsonPath("$.authorId", is(1)));

		verify(authorService, times(1)).getAuthorById(authorId);
	}

	@Test
	public void testGetAuthorByFirstName() throws Exception {
		String firstName = "Hero";

		AuthorDto authorDto = new AuthorDto();
		authorDto.setAuthorId(1);
		authorDto.setFirstName(firstName);
		authorDto.setLastName("Machogeek");

		when(authorService.getAuthorByFirstName(firstName)).thenReturn(authorDto);

		mockMvc.perform(get("/author/firstname/{firstName}", firstName)).andExpect(status().isOk())
				.andExpect(jsonPath("$.authorId", is(1))).andExpect(jsonPath("$.firstName", is("Hero")))
				.andExpect(jsonPath("$.lastName", is("Machogeek")));

		verify(authorService, times(1)).getAuthorByFirstName(firstName);
	}

	@Test
	public void testGetAuthorByLastName() throws Exception {
		String lastName = "Machogeek";

		AuthorDto authorDto = new AuthorDto();
		authorDto.setAuthorId(1);
		authorDto.setFirstName("Hero");
		authorDto.setLastName(lastName);

		when(authorService.getAuthorByLastName(lastName)).thenReturn(authorDto);

		mockMvc.perform(get("/author/lastname/{lastName}", lastName)).andExpect(status().isOk())
				.andExpect(jsonPath("$.authorId", is(1))).andExpect(jsonPath("$.firstName", is("Hero")))
				.andExpect(jsonPath("$.lastName", is("Machogeek")));

		verify(authorService, times(1)).getAuthorByLastName(lastName);
	}

	@Test
	public void testUpdateFirstNameByAuthorId() throws Exception {
		int authorId = 1;

		AuthorDto authorDto = new AuthorDto();
		authorDto.setAuthorId(authorId);
		authorDto.setFirstName("Hero");

		AuthorDto updatedAuthorDto = new AuthorDto();
		updatedAuthorDto.setAuthorId(authorId);
		updatedAuthorDto.setFirstName("John");

		when(authorService.updateFirstNameById(eq(authorId), any(AuthorDto.class))).thenReturn(updatedAuthorDto);

		mockMvc.perform(put("/author/update/firstname/{authorId}", authorId).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(authorDto))).andExpect(status().isOk()).andExpect(jsonPath("$.authorId", is(1)))
				.andExpect(jsonPath("$.firstName", is("John")));

		verify(authorService, times(1)).updateFirstNameById(eq(authorId), any(AuthorDto.class));
	}

	@Test
	public void testUpdateLastNameByAuthorId() throws Exception {
		int authorId = 1;

		AuthorDto authorDto = new AuthorDto();
		authorDto.setAuthorId(authorId);
		authorDto.setLastName("Machogeek");

		AuthorDto updatedAuthorDto = new AuthorDto();
		updatedAuthorDto.setAuthorId(authorId);
		updatedAuthorDto.setLastName("Smith");

		when(authorService.updateLastNameById(eq(authorId), any(AuthorDto.class))).thenReturn(updatedAuthorDto);

		mockMvc.perform(put("/author/update/lastname/{authorId}", authorId).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(authorDto))).andExpect(status().isOk()).andExpect(jsonPath("$.authorId", is(1)))
				.andExpect(jsonPath("$.lastName", is("Smith")));

		verify(authorService, times(1)).updateLastNameById(eq(authorId), any(AuthorDto.class));
	}
//	@Test
//	public void testUpdateLastNameByAuthorId() throws Exception {
//	    int authorId = 1;
//
//	    AuthorDto authorDto = new AuthorDto();
//	    authorDto.setAuthorId(authorId);
//	    authorDto.setLastName("Machogeek");
//
//	    AuthorDto updatedAuthorDto = new AuthorDto();
//	    updatedAuthorDto.setAuthorId(authorId);
//	    updatedAuthorDto.setLastName("Smith");
//
//	    when(authorService.updateLastNameById(eq(authorId), any(AuthorDto.class))).thenReturn(updatedAuthorDto);
//
//	    mockMvc.perform(put("/author/update/lastname/{authorId}", authorId)
//	            .contentType(MediaType.APPLICATION_JSON)
//	            .content(asJsonString(authorDto)))
//	            .andExpect(status().isOk())
//	            .andExpect(jsonPath("$.authorId", is(1)))
//	            .andExpect(jsonPath("$.lastName", is("Smith")));
//
//	    verify(authorService, times(1)).updateLastNameById(eq(authorId), any(AuthorDto.class));
//	}


	public static String asJsonString(Object object) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testGetAllBooksByAuthorId() throws Exception {
		int authorId = 1;

		AuthorDto authorDto = new AuthorDto();
		authorDto.setAuthorId(authorId);
		authorDto.setFirstName("Hero");
		authorDto.setLastName("Machogeek");

		BookDto book1 = new BookDto();
		book1.setIsbn("1-111-11111-4");
		book1.setTitle("Women are From Venus ORACLE is from Beyond Pluto");

		List<BookDto> books = Arrays.asList(book1);

		when(authorService.getAllBooksByAuthorId(authorId)).thenReturn(books);

		mockMvc.perform(get("/author/books/{authorId}", authorId)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].isbn", is("1-111-11111-4")))
				.andExpect(jsonPath("$[0].title", is("Women are From Venus ORACLE is from Beyond Pluto")));

		verify(authorService, times(1)).getAllBooksByAuthorId(authorId);
	}
	@Test
    public void testAddAuthorInvalidInput() throws Exception {

    AuthorDto authorDto = new AuthorDto();
    authorDto.setAuthorId(28);
    authorDto.setFirstName(null); // Invalid null value for first name
    authorDto.setLastName(null);

    // Validate the DTO using a Validator

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<AuthorDto>> violations = validator.validate(authorDto);

    // Expect a violation for the firstName field being null

    assertThat(violations)
            .extracting(ConstraintViolation::getPropertyPath)
            .extracting(Object::toString)
            .contains("firstName")
    		.contains("lastName");

    // Perform the test with the invalid DTO

    mockMvc.perform(post("/author/post")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(authorDto)))
            .andExpect(status().isBadRequest());

    verify(authorService, times(0)).saveAuthor(authorDto); // Ensure saveAuthor is not called
}

	@Test

	public void testGetAuthorByIdNotFound() throws Exception {

		int authorId = 1;
		AuthorDto authorDto = new AuthorDto();
		authorDto.setAuthorId(authorId);
		when(authorService.getAuthorById(authorId)).thenThrow(new AuthorNotFoundException("No Author Found!"));
		mockMvc.perform(get("/author/{authorId}", authorId)).andExpect(status().isNotFound());

		verify(authorService, times(1)).getAuthorById(authorId);

	}

	@Test
	public void testGetAllBooksByAuthorIdInvalidAuthorId() throws Exception {
		int authorId = 999;

		when(authorService.getAllBooksByAuthorId(authorId))
				.thenThrow(new AuthorNotFoundException("Author not found with id: 999"));

		mockMvc.perform(get("/author/books/{authorId}", authorId)).andExpect(status().isNotFound());

		verify(authorService, times(1)).getAllBooksByAuthorId(authorId);
	}

	@Test
	public void testGetAuthorByFirstNameInvalidAuthorName() throws Exception {
		String firstName = "Hero";

		when(authorService.getAuthorByFirstName(firstName))
				.thenThrow(new AuthorNotFoundException("Author not found with name: Hero"));

		mockMvc.perform(get("/author/firstname/{firstName}", firstName)).andExpect(status().isNotFound());

		verify(authorService, times(1)).getAuthorByFirstName(firstName);
	}

	@Test
	public void testGetAuthorByLastNameInvalidAuthorName() throws Exception {
		String lastName = "Machogeek";

		when(authorService.getAuthorByLastName(lastName))
				.thenThrow(new AuthorNotFoundException("Author not found with lastname: Machogeek"));

		mockMvc.perform(get("/author/lastname/{lastName}", lastName)).andExpect(status().isNotFound());

		verify(authorService, times(1)).getAuthorByLastName(lastName);
	}

	@Test
	public void testUpdateFirstNameByAuthorIdInvalidAuthorId() throws Exception {
		int authorId = -1;

		AuthorDto authorDto = new AuthorDto();
		authorDto.setAuthorId(authorId);
		authorDto.setFirstName("John");

		AuthorDto updatedAuthorDto = new AuthorDto();
		updatedAuthorDto.setAuthorId(authorId);
		updatedAuthorDto.setFirstName("John");

		when(authorService.updateFirstNameById(eq(authorId), any(AuthorDto.class)))
				.thenThrow(new AuthorNotFoundException("Author not found with ID: -1"));

		mockMvc.perform(put("/author/update/firstname/{authorId}", authorId).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(authorDto))).andExpect(status().isNotFound());

		verify(authorService, times(1)).updateFirstNameById(eq(authorId), any(AuthorDto.class));
	}

	@Test
	public void testUpdateLastNameByAuthorIdInvalidAuthorId() throws Exception {
		int authorId = -1;

		AuthorDto authorDto = new AuthorDto();
		authorDto.setAuthorId(authorId);
		authorDto.setLastName("Smith");

		AuthorDto updatedAuthorDto = new AuthorDto();
		updatedAuthorDto.setAuthorId(authorId);
		updatedAuthorDto.setLastName("John");

		when(authorService.updateLastNameById(eq(authorId), any(AuthorDto.class)))
				.thenThrow(new AuthorNotFoundException("Author not found with ID: -1"));

		mockMvc.perform(put("/author/update/lastname/{authorId}", authorId).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(authorDto))).andExpect(status().isNotFound());

		verify(authorService, times(1)).updateLastNameById(eq(authorId), any(AuthorDto.class));
	}

}