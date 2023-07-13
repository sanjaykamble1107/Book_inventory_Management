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

import com.bookinventorymanagement.dto.AuthorDto;
import com.bookinventorymanagement.dto.BookDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.service.AuthorService;

import jakarta.validation.Valid;
@CrossOrigin(origins="http://localhost:4200/")

@RestController
@RequestMapping("/author")
@Validated
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	// Add a new author
	@PostMapping("/post")
	public ResponseEntity<ResponseDto> addAuthor(@Valid @RequestBody AuthorDto authorDto) {
		ResponseDto dto = authorService.saveAuthor(authorDto);
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.OK);
	}

	// Get an author by ID
	@GetMapping("/{authorId}")
	public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Integer authorId) {
		AuthorDto dto = authorService.getAuthorById(authorId);
		return new ResponseEntity<AuthorDto>(dto, HttpStatus.OK);
	}

	// Get an author by first name
	@GetMapping("/firstname/{firstName}")
	public ResponseEntity<AuthorDto> getAuthorByFirstName(@PathVariable String firstName) {
		AuthorDto dto = authorService.getAuthorByFirstName(firstName);
		return new ResponseEntity<AuthorDto>(dto, HttpStatus.OK);
	}

	// Get an author by last name
	@GetMapping("/lastname/{lastName}")
	public ResponseEntity<AuthorDto> getAuthorByLastName(@PathVariable String lastName) {
		AuthorDto dto = authorService.getAuthorByLastName(lastName);
		return new ResponseEntity<AuthorDto>(dto, HttpStatus.OK);
	}

	// Update the first name of an author by ID
	@PutMapping("/update/firstname/{authorId}")
	public ResponseEntity<AuthorDto> updateFirstNameByAuthorId(@PathVariable Integer authorId,
			@RequestBody AuthorDto authorDto) {
		AuthorDto dto = authorService.updateFirstNameById(authorId, authorDto);
		return new ResponseEntity<AuthorDto>(dto, HttpStatus.OK);
	}

	// Update the last name of an author by ID
	@PutMapping("/update/lastname/{authorId}")
	public ResponseEntity<AuthorDto> updateLastNameByAuthorId(@PathVariable Integer authorId,
			@RequestBody AuthorDto authorDto) {
		AuthorDto dto = authorService.updateLastNameById(authorId, authorDto);
		return new ResponseEntity<AuthorDto>(dto, HttpStatus.OK);
	}

	// Get all books by author ID
	@GetMapping("/books/{authorId}")
	public ResponseEntity<List<BookDto>> getAllBooksByAuthorId(@PathVariable Integer authorId) {
		List<BookDto> books = authorService.getAllBooksByAuthorId(authorId);
		return new ResponseEntity<List<BookDto>>(books, HttpStatus.OK);
	}
}
