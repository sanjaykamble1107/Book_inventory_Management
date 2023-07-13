package com.bookinventorymanagement.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.dto.CategoryDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.entity.Category;
import com.bookinventorymanagement.exceptions.CategoryNotFoundException;
import com.bookinventorymanagement.exceptions.PublisherNotFoundException;
import com.bookinventorymanagement.repository.CategoryRepository;
import com.bookinventorymanagement.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Save a new category.
	 * 
	 * @param categoryDto The CategoryDto containing the category details to be
	 *                    saved.
	 * @return The ResponseDto with a success message.
	 * @throws CategoryNotFoundException if a category with the same ID already
	 *                                   exists.
	 */
	@Override
	public ResponseDto saveCategory(CategoryDto categoryDto) {
		Optional<Category> optional = categoryRepository.findById(categoryDto.getCatId());
		if (optional.isPresent()) {
			throw new CategoryNotFoundException(
					"Category is already present with category ID: " + categoryDto.getCatId());
		}
		Category category = new Category();
		BeanUtils.copyProperties(categoryDto, category);
		responseDto.setResponseMessage("Category added successfully");
		categoryRepository.save(category);
		return responseDto;
	}

	/**
	 * Update the description of a category by its ID.
	 * 
	 * @param catId The ID of the category.
	 * @param dto   The CategoryDto containing the updated category details.
	 * @return The CategoryDto containing the updated category details.
	 * @throws CategoryNotFoundException if the category with the given ID is not
	 *                                   found.
	 */
	@Override
	public CategoryDto updateDescriptionById(Integer catId, CategoryDto dto) {
		Optional<Category> optionalCategory = categoryRepository.findById(catId);
		if (optionalCategory.isPresent()) {
			Category category = optionalCategory.get();
			category.setCatDescription(dto.getCatDescription());
			categoryRepository.save(category);
			CategoryDto categoryDtos = new CategoryDto();
			BeanUtils.copyProperties(category, categoryDtos);
			
			return categoryDtos;
		}

		throw new CategoryNotFoundException("Category not found with ID: " + catId);
	}

	/**
	 * Get a category by its ID.
	 * 
	 * @param catId The ID of the category.
	 * @return The CategoryDto containing the category details.
	 * @throws CategoryNotFoundException if the category with the given ID is not
	 *                                   found.
	 */
	@Override
	public CategoryDto getCategoryById(Integer catId) {
		Optional<Category> findById = categoryRepository.findById(catId);
		if (findById.isPresent()) {
			CategoryDto categoryDto = new CategoryDto();
			BeanUtils.copyProperties(findById.get(), categoryDto);
			return categoryDto;
		}
		throw new CategoryNotFoundException("Category not found with ID: " + catId);
	}

	/**
	 * Get all categories.
	 * 
	 * @return The list of CategoryDto containing all category details.
	 * @throws PublisherNotFoundException if there are no categories available.
	 */
	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepository.findAll();

		if (categories.isEmpty()) {
			throw new PublisherNotFoundException("There are no categories available");
		}

		List<CategoryDto> dtos = new ArrayList<>();

		for (Category category : categories) {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(category, dto);
			dtos.add(dto);
		}
		return dtos;
	}
}
