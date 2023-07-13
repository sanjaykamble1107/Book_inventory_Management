package com.bookinventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookinventorymanagement.dto.CategoryDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.service.CategoryService;

import jakarta.validation.Valid;


@CrossOrigin(origins="http://localhost:4200/")

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	// Save a category
	@PostMapping("/post")
	public ResponseEntity<ResponseDto> saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
		ResponseDto dto = categoryService.saveCategory(categoryDto);
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.OK);
	}
	
	// Update the description of a category by category ID
	@PutMapping("/update/description/{catId}")
	public ResponseEntity<CategoryDto> updateDescriptionById(@PathVariable Integer catId,
			@RequestBody CategoryDto categoryDto) {
		CategoryDto catDto = categoryService.updateDescriptionById(catId, categoryDto);
		return new ResponseEntity<CategoryDto>(catDto, HttpStatus.OK);
	}
	
	// Get a category by category ID
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer catId) {
		CategoryDto categoryDto = categoryService.getCategoryById(catId);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
	
	// Get all categories
	@GetMapping("/getall")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		List<CategoryDto> categoryDto = categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(categoryDto, HttpStatus.OK);
	}
}
