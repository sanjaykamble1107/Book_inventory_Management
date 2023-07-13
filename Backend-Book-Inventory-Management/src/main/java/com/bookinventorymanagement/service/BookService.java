package com.bookinventorymanagement.service;

import java.util.List;

import com.bookinventorymanagement.dto.BookDto;
import com.bookinventorymanagement.dto.ResponseDto;

public interface BookService {

	public ResponseDto saveBook(BookDto bookDto);

	public List<BookDto> getAllBooks();

	public BookDto getBookByIsbn(String isbn);

	public BookDto getBookByTitle(String title);

	public List<BookDto> getBookByPublisherId(Integer publisherId);

	public List<BookDto> getBookByCategory(Integer category);

	public BookDto updateTitleByIsbn(String isbn, BookDto bookDto);

	public BookDto updateDescriptionByIsbn(String isbn, BookDto bookDto);

	public BookDto updateEditionByIsbn(String isbn, BookDto bookDto);

	public BookDto updateCategoryByIsbn(String isbn, BookDto bookDto);

	public BookDto upatePublisherIdByIsbn(String isbn, BookDto bookDto);

}