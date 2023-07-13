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

import com.bookinventorymanagement.dto.BookReviewDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.service.BookReviewService;

import jakarta.validation.Valid;
@CrossOrigin(origins="http://localhost:4200/")

@RestController
@RequestMapping("/bookreview")
@Validated
public class BookReviewController {

	@Autowired
	private BookReviewService bookReviewService;

	// Save a book review
	@PostMapping("/post")
	public ResponseEntity<ResponseDto> saveBookReview(@Valid @RequestBody BookReviewDto bookReviewDto) {
		ResponseDto dto = bookReviewService.saveBookReview(bookReviewDto);
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.OK);
	}

	// Get book reviews by ISBN
	@GetMapping("/{isbn}")
	public ResponseEntity<List<BookReviewDto>> getBookReviewByIsbn(@PathVariable String isbn) {
		List<BookReviewDto> dto = bookReviewService.getBookReviewByIsbn(isbn);
		return new ResponseEntity<List<BookReviewDto>>(dto, HttpStatus.OK);
	}

	// Update the rating of a book review by ISBN
	@PutMapping("/update/rating/{isbn}")
	public ResponseEntity<List<BookReviewDto>> updateRatingByIsbn(@PathVariable String isbn,
			@RequestBody BookReviewDto bookReviewDto) {
		List<BookReviewDto> dto = bookReviewService.updateRatingByIsbn(isbn, bookReviewDto);
		return new ResponseEntity<List<BookReviewDto>>(dto, HttpStatus.OK);
	}

	// Update the comments of a book review by ISBN
	@PutMapping("/update/comments/{isbn}")
	public ResponseEntity<List<BookReviewDto>> updateCommentByIsbn(@PathVariable String isbn,
			@RequestBody BookReviewDto bookReviewDto) {
		List<BookReviewDto> dto = bookReviewService.updateCommentsByIsbn(isbn, bookReviewDto);
		return new ResponseEntity<List<BookReviewDto>>(dto, HttpStatus.OK);
	}

}
