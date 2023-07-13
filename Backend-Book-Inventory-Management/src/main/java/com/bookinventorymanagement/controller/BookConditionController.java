package com.bookinventorymanagement.controller;

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

import com.bookinventorymanagement.dto.BookConditionDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.service.BookConditionService;

import jakarta.validation.Valid;
@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/bookcondition")
@Validated
public class BookConditionController {

	@Autowired
	private BookConditionService conditionService;
	

	// Save a book condition
	@PostMapping("/post")
	public ResponseEntity<ResponseDto> saveCondition(@Valid @RequestBody BookConditionDto conditionDto) {
		ResponseDto dto = conditionService.saveBookCondition(conditionDto);
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.OK);
	}

	// Get a book condition by ranks
	@GetMapping("/{ranks}")
	public ResponseEntity<BookConditionDto> getBookConditionById(@PathVariable Integer ranks) {
		BookConditionDto conditionDto = conditionService.getBookConditionById(ranks);
		return new ResponseEntity<BookConditionDto>(conditionDto, HttpStatus.OK);
	}

	// Update the description of a book condition by ranks
	@PutMapping("/update/description/{ranks}")
	public ResponseEntity<BookConditionDto> updateDescriptionById(@PathVariable Integer ranks,
			@RequestBody BookConditionDto conditionDto) {
		BookConditionDto conditionDtos = conditionService.updateDescriptionById(ranks, conditionDto);
		return new ResponseEntity<BookConditionDto>(conditionDtos, HttpStatus.OK);
	}

	// Update the full description of a book condition by ranks
	@PutMapping("/update/fulldescription/{ranks}")
	public ResponseEntity<?> updateFullDescriptionById(@PathVariable Integer ranks,
			@RequestBody BookConditionDto conditionDto) {
		BookConditionDto conditionDtos = conditionService.updateFullDescriptionById(ranks, conditionDto);
		return new ResponseEntity<BookConditionDto>(conditionDtos, HttpStatus.OK);
	}

	// Update the price of a book condition by ranks
	@PutMapping("/update/price/{ranks}")
	public ResponseEntity<BookConditionDto> updatepriceById(@PathVariable Integer ranks,
			@RequestBody BookConditionDto conditionDto) {
		BookConditionDto conditionDtos = conditionService.updatePriceById(ranks, conditionDto);
		return new ResponseEntity<BookConditionDto>(conditionDtos, HttpStatus.OK);
	}
}
