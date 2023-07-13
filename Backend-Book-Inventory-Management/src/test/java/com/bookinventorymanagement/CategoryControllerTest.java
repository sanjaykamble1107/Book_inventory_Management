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

import com.bookinventorymanagement.dto.CategoryDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.exceptions.CategoryNotFoundException;
import com.bookinventorymanagement.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {
	
	@MockBean
	private CategoryService categoryService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setupAuthentication() {
		Authentication auth = new UsernamePasswordAuthenticationToken("Aadesh_d", "123",
				Collections.singletonList(new SimpleGrantedAuthority("Admin")));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	@Test
	public void testSaveCategory() throws Exception
	{
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setCatId(11);
		categoryDto.setCatDescription("Romance");
		ResponseDto responseDto=new ResponseDto();
		responseDto.setResponseMessage("category added successfully");
		
		when(categoryService.saveCategory(categoryDto)).thenReturn(responseDto);

		
		mockMvc.perform(post("/category/post")
	        		.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(categoryDto)))
	        		.andExpect(status().isOk());
		  verify(categoryService, times(1)).saveCategory(any(CategoryDto.class));
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
	public void testUpdateDescriptionById() throws Exception
	{
		Integer catId=11;
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setCatId(catId);
		categoryDto.setCatDescription("Romance");
		
		CategoryDto updateCategoryDto=new CategoryDto();
		updateCategoryDto.setCatId(catId);
		updateCategoryDto.setCatDescription("Science");
		
		when(categoryService.updateDescriptionById(eq(catId),any(CategoryDto.class))).thenReturn(updateCategoryDto);
    	mockMvc.perform(put("/category/update/description/{catId}",catId)
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(categoryDto)))
        		.andExpect(status().isOk())
				.andExpect(jsonPath("$.catId", is(11)))
				.andExpect(jsonPath("$.catDescription", is("Science")));
				
        
    	verify(categoryService, times(1)).updateDescriptionById(eq(catId),any(CategoryDto.class));
		
	}
	
	@Test
	public void testGetCategoryById() throws Exception
	{
		Integer catId=11;
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setCatId(catId);
		
		when(categoryService.getCategoryById(catId)).thenReturn(categoryDto);
		mockMvc.perform(get("/category/{catId}",catId))
        		.andExpect(status().isOk())
				.andExpect(jsonPath("$.catId", is(11)));
				
		  verify(categoryService, times(1)).getCategoryById(catId);
	}
	
	@Test
	public void testSaveCategoryInvalidInput() throws Exception
	{  
	CategoryDto categoryDto = new CategoryDto();
    categoryDto.setCatId(null);// Invalid null value for categoryID
    categoryDto.setCatDescription("Love"); 

 

    // Validate the DTO using a Validator
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<CategoryDto>> violations = validator.validate(categoryDto);

 

    // Expect a violation for the catDescription field being null
    assertThat(violations)
            .extracting(ConstraintViolation::getPropertyPath)
            .extracting(Object::toString)
            .contains("catId");

 

    // Perform the test with the invalid DTO
    mockMvc.perform(post("/category/post")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(categoryDto)))
            .andExpect(status().isBadRequest());

 

    verify(categoryService, times(0)).saveCategory(any(CategoryDto.class)); // Ensure saveCategory is not called
	}

	@Test
	public void testUpdateDescriptionByIdInvalidId() throws Exception
	{
		Integer catId=-11;
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setCatId(catId);
		categoryDto.setCatDescription("Romance");
		
		CategoryDto updateCategoryDto=new CategoryDto();
		updateCategoryDto.setCatId(catId);
		updateCategoryDto.setCatDescription("Science");
		
		when(categoryService.updateDescriptionById(eq(catId),any( CategoryDto.class)))
		.thenThrow(new CategoryNotFoundException("Category not found with Id:"+catId));

    	mockMvc.perform(put("/category/update/description/{catId}",catId)
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(asJsonString(categoryDto)))
    			.andExpect(status().isNotFound());

		verify(categoryService, times(1)).updateDescriptionById(eq(catId),any( CategoryDto.class));
}

	@Test
	public void testGetCategoryByIdInvalidId() throws Exception
	{
		Integer catId=11;
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setCatId(catId);
		
		when(categoryService.getCategoryById(catId))
		.thenThrow(new CategoryNotFoundException("Category not found with Id:"+catId));

		mockMvc.perform(get("/category/{catId}",catId))
		.andExpect(status().isNotFound());

		verify(categoryService, times(1)).getCategoryById(catId);
	}

}
