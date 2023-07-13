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

import com.bookinventorymanagement.dto.BookDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.service.BookService;

import jakarta.validation.Valid;

@CrossOrigin(origins="http://localhost:4200/")

@RestController
@RequestMapping("/book")
@Validated
public class BookController {

	@Autowired
	private BookService bookService;

	// Get all books
	@GetMapping
	public ResponseEntity<List<BookDto>> getAllBooks() {
		List<BookDto> bookDtos = bookService.getAllBooks();
		return new ResponseEntity<List<BookDto>>(bookDtos, HttpStatus.OK);
	}

	// Save a book
	@PostMapping("/post")
	public ResponseEntity<ResponseDto> saveBook(@Valid @RequestBody BookDto bookDto) {
		ResponseDto dto = bookService.saveBook(bookDto);
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.OK);
	}

	// Get a book by ISBN
	@GetMapping("/{isbn}")
	public ResponseEntity<BookDto> getBookByIsbn(@PathVariable String isbn) {
		BookDto bookDto = bookService.getBookByIsbn(isbn);
		return new ResponseEntity<BookDto>(bookDto, HttpStatus.OK);
	}

	// Get a book by title
	@GetMapping("/title/{title}")
	public ResponseEntity<BookDto> getBookByTitle(@PathVariable String title) {
		BookDto bookDto = bookService.getBookByTitle(title);
		return new ResponseEntity<BookDto>(bookDto, HttpStatus.OK);
	}

	// Update the title of a book by ISBN
	@PutMapping("/update/title/{isbn}")
	public ResponseEntity<BookDto> updateTitleByIsbn(@PathVariable String isbn, @RequestBody BookDto bookDto) {
		BookDto bookDtos = bookService.updateTitleByIsbn(isbn, bookDto);
		return new ResponseEntity<BookDto>(bookDtos, HttpStatus.OK);
	}

	// Get books by publisher ID
	@GetMapping("/publisher/{publisherId}")
	public ResponseEntity<List<BookDto>> getBookByPublisherId(@PathVariable Integer publisherId) {
		List<BookDto> bookDtos = bookService.getBookByPublisherId(publisherId);
		return new ResponseEntity<List<BookDto>>(bookDtos, HttpStatus.OK);
	}

	// Get books by category
	@GetMapping("/category/{category}")
	public ResponseEntity<List<BookDto>> getBookByCategory(@PathVariable Integer category) {
		List<BookDto> bookDtos = bookService.getBookByCategory(category);
		return new ResponseEntity<List<BookDto>>(bookDtos, HttpStatus.OK);
	}

	// Update the description of a book by ISBN
	@PutMapping("/update/description/{isbn}")
	public ResponseEntity<BookDto> updateDescriptionByIsbn(@PathVariable String isbn, @RequestBody BookDto bookDto) {
		BookDto bookDtos = bookService.updateDescriptionByIsbn(isbn, bookDto);
		return new ResponseEntity<BookDto>(bookDtos, HttpStatus.OK);
	}

	// Update the edition of a book by ISBN
	@PutMapping("/update/edition/{isbn}")
	public ResponseEntity<BookDto> updateEditionByIsbn(@PathVariable String isbn,  @RequestBody BookDto bookDto) {
		BookDto bookDtos = bookService.updateEditionByIsbn(isbn, bookDto);
		return new ResponseEntity<BookDto>(bookDtos, HttpStatus.OK);
	}

	// Update the category of a book by ISBN
	@PutMapping("/update/category/{isbn}")
	public ResponseEntity<BookDto> updateCategoryByIsbn(@PathVariable String isbn, @RequestBody BookDto bookDto) {
		BookDto bookDtos = bookService.updateCategoryByIsbn(isbn, bookDto);
		return new ResponseEntity<BookDto>(bookDtos, HttpStatus.OK);
	}

	// Update the publisher ID of a book by ISBN
	@PutMapping("/update/publisher/{isbn}")
	public ResponseEntity<BookDto> updatePublisherIdByIsbn(@PathVariable String isbn, @RequestBody BookDto bookDto) {
		BookDto bookDtos = bookService.upatePublisherIdByIsbn(isbn, bookDto);
		return new ResponseEntity<BookDto>(bookDtos, HttpStatus.OK);
	}

}
