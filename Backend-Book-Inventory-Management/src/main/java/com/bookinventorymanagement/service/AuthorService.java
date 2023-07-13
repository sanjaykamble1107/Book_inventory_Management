package com.bookinventorymanagement.service;

import java.util.List;

import com.bookinventorymanagement.dto.AuthorDto;
import com.bookinventorymanagement.dto.BookDto;
import com.bookinventorymanagement.dto.ResponseDto;

public interface AuthorService {
	
	public ResponseDto saveAuthor(AuthorDto authorDto);
	
	public AuthorDto getAuthorById(Integer authorId);
	
	public AuthorDto getAuthorByFirstName(String firstName);

	public AuthorDto getAuthorByLastName(String lastName);

	public AuthorDto updateFirstNameById(Integer authorId, AuthorDto authorDto);

	public AuthorDto updateLastNameById(Integer authorId, AuthorDto authorDto);
	
	public List<BookDto> getAllBooksByAuthorId(Integer authorId);



}
