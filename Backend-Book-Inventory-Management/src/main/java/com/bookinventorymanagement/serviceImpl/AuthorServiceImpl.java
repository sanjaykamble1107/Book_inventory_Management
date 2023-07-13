package com.bookinventorymanagement.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.dto.AuthorDto;
import com.bookinventorymanagement.dto.BookDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.entity.Author;
import com.bookinventorymanagement.entity.Book;
import com.bookinventorymanagement.entity.BookAuthor;
import com.bookinventorymanagement.exceptions.AuthorNotFoundException;
import com.bookinventorymanagement.repository.AuthorRepository;
import com.bookinventorymanagement.repository.BookAuthorRepository;
import com.bookinventorymanagement.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookAuthorRepository bookAuthorRepository;

	private ResponseDto responseDto = new ResponseDto();

	// Save a new author
	@Override
	public ResponseDto saveAuthor(AuthorDto authorDto) {
		// Check if author with the given ID already exists
		Optional<Author> optional = authorRepository.findById(authorDto.getAuthorId());
		if (optional.isPresent()) {
			throw new AuthorNotFoundException("Author is already present with authorId:" + authorDto.getAuthorId());
		}

		Author author = new Author();
		BeanUtils.copyProperties(authorDto, author);
		authorRepository.save(author);
		responseDto.setResponseMessage("Author added successfully");
		return responseDto;
	}

	// Get an author by ID
	@Override
	public AuthorDto getAuthorById(Integer authorId) {
		Optional<Author> optional = authorRepository.findById(authorId);
		if (optional.isPresent()) {
			AuthorDto authorDto = new AuthorDto();
			BeanUtils.copyProperties(optional.get(), authorDto);
			return authorDto;
		}

		throw new AuthorNotFoundException("Author not found with id : " + authorId);
	}

	// Get an author by first name
	@Override
	public AuthorDto getAuthorByFirstName(String firstName) {
		Optional<Author> optional = authorRepository.findByFirstName(firstName);
		if (optional.isPresent()) {
			AuthorDto authorDto = new AuthorDto();
			BeanUtils.copyProperties(optional.get(), authorDto);
			return authorDto;
		}
		throw new AuthorNotFoundException("Author not found with firstname" + firstName);

	}

	// Get an author by last name
	@Override
	public AuthorDto getAuthorByLastName(String lastName) {
		Optional<Author> optional = authorRepository.findByLastName(lastName);
		if (optional.isPresent()) {
			AuthorDto authorDto = new AuthorDto();
			BeanUtils.copyProperties(optional.get(), authorDto);
			return authorDto;
		}

		throw new AuthorNotFoundException("Author not found with lastname" + lastName);
	}

	// Update the first name of an author by ID
	@Override
	public AuthorDto updateFirstNameById(Integer authorId, AuthorDto authorDto) {
		Optional<Author> optional = authorRepository.findById(authorId);
		if (optional.isPresent()) {
			AuthorDto dto = getAuthorById(authorId);
			dto.setFirstName(authorDto.getFirstName());
			Author author = new Author();
			BeanUtils.copyProperties(dto, author);
			authorRepository.save(author);
			return dto;
		}
		throw new AuthorNotFoundException("Author not found with id:" + authorId);
	}

	// Update the last name of an author by ID
	@Override
	public AuthorDto updateLastNameById(Integer authorId, AuthorDto authorDto) {
		Optional<Author> optional = authorRepository.findById(authorId);
		if (optional.isPresent()) {
			AuthorDto dto = getAuthorById(authorId);
			dto.setLastName(authorDto.getLastName());
			Author author = new Author();
			BeanUtils.copyProperties(dto, author);
			authorRepository.save(author);
			return dto;
		}
		throw new AuthorNotFoundException("Author not found with id:" + authorId);
	}

	// Get all books by author ID
	@Override
	public List<BookDto> getAllBooksByAuthorId(Integer authorId) {
		Optional<Author> optional = authorRepository.findById(authorId);

		if (optional.isPresent()) {
			Author author = optional.get();
			List<BookAuthor> bookAuthors = bookAuthorRepository.findByAuthorID(author);
			List<Book> books = bookAuthors.stream().map(BookAuthor::getIsbn).collect(Collectors.toList());

			List<BookDto> dtos = new ArrayList<>();
			for (Book book : books) {
				BookDto dto = new BookDto();
				BeanUtils.copyProperties(book, dto);
				dto.setCategory(book.getCatId().getCatId());
				dto.setPublisherId(book.getPublisherId().getPublisherId());
				dtos.add(dto);
			}

			return dtos;
		}
		throw new AuthorNotFoundException("Author not found with id :" + authorId);
	}
}
