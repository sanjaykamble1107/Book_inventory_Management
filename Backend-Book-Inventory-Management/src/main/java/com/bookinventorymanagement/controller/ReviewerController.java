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

import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.dto.ReviewerDto;
import com.bookinventorymanagement.service.ReviewerService;

import jakarta.validation.Valid;


@CrossOrigin(origins="http://localhost:4200/")

@RestController
@RequestMapping("/reviewer")
@Validated
public class ReviewerController {
	
	@Autowired
	private ReviewerService reviewerService;
	
	// Save a reviewer
	@PostMapping("/post")
	public ResponseEntity<ResponseDto> saveReviewer(@Valid @RequestBody ReviewerDto reviewerDto) {
		ResponseDto dto = reviewerService.saveReviewer(reviewerDto);
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.OK);
	}
	
	// Get a reviewer by ID
	@GetMapping("/{id}")
	public ResponseEntity<ReviewerDto> getReviwerById(@PathVariable Integer id) {
		ReviewerDto reviewerDto= reviewerService.getReviewerById(id);
		return new ResponseEntity<ReviewerDto>(reviewerDto, HttpStatus.OK);
	}
	
	// Update a reviewer by ID
	@PutMapping("/update/name/{id}")
	public ResponseEntity<ReviewerDto> updateReviewerById(@PathVariable Integer id, @RequestBody ReviewerDto reviewerDto) {
		ReviewerDto reviewerDtos = reviewerService.updateReviewerById(id, reviewerDto);
		return new ResponseEntity<ReviewerDto>(reviewerDtos, HttpStatus.OK);
	}
	
	// Update the employer of a reviewer by ID
	@PutMapping("/update/employedby/{id}")
	public ResponseEntity<ReviewerDto> updateEmployeeById(@PathVariable Integer id, @RequestBody ReviewerDto reviewerDto) {
		ReviewerDto reviewerDtos = reviewerService.updateEmployeeById(id, reviewerDto);
		return new ResponseEntity<ReviewerDto>(reviewerDtos, HttpStatus.OK);
	}
}
