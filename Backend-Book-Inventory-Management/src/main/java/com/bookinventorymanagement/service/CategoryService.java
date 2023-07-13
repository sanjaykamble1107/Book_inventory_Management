package com.bookinventorymanagement.service;

import java.util.List;

import com.bookinventorymanagement.dto.CategoryDto;
import com.bookinventorymanagement.dto.ResponseDto;

public interface CategoryService {

	public ResponseDto saveCategory(CategoryDto categoryDto);
	public CategoryDto updateDescriptionById(Integer catId,CategoryDto categoryDto);
	public CategoryDto getCategoryById(Integer catId);
	public List<CategoryDto> getAllCategory();
}
