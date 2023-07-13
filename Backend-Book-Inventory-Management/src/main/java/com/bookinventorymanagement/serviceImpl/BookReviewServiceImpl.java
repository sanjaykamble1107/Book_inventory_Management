package com.bookinventorymanagement.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.dto.BookReviewDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.entity.Book;
import com.bookinventorymanagement.entity.BookReview;
import com.bookinventorymanagement.entity.Reviewer;
import com.bookinventorymanagement.exceptions.BookNotFoundException;
import com.bookinventorymanagement.exceptions.BookReviewNotFoundException;
import com.bookinventorymanagement.exceptions.ReviewerNotFoundException;
import com.bookinventorymanagement.repository.BookRepository;
import com.bookinventorymanagement.repository.BookReviewRepository;
import com.bookinventorymanagement.repository.ReviewerRepository;
import com.bookinventorymanagement.service.BookReviewService;

@Service
public class BookReviewServiceImpl implements BookReviewService {

	@Autowired
	private BookReviewRepository bookReviewRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ReviewerRepository reviewerRepository;

	private ResponseDto responseDto = new ResponseDto();

	@Override
	public ResponseDto saveBookReview(BookReviewDto bookReviewDto) {

		// Create a new BookReview instance
		BookReview bookReview = new BookReview();

		// Find the book by ISBN
		Optional<Book> book = bookRepository.findByIsbn(bookReviewDto.getIsbn());
		if (book.isEmpty()) {
			// Throw an exception if the book is not found
			throw new BookNotFoundException("Book not found with ISBN: " + bookReviewDto.getIsbn());
		}

		// Find the reviewer by ID
		Optional<Reviewer> reviewer = reviewerRepository.findById(bookReviewDto.getReviewerID());
		if (reviewer.isEmpty()) {
			// Throw an exception if the reviewer is not found
			throw new ReviewerNotFoundException("Reviewer not found with ID: " + bookReviewDto.getReviewerID());
		}

		// Set the reviewer and book for the book review
		bookReview.setReviewerId(reviewer.get());
		bookReview.setIsbn(book.get());

		// Copy properties from DTO to the book review entity
		BeanUtils.copyProperties(bookReviewDto, bookReview);

		// Set the response message
		responseDto.setResponseMessage("Book review added successfully");

		// Save the book review
		bookReviewRepository.save(bookReview);

		return responseDto;
	}

	@Override
	public List<BookReviewDto> getBookReviewByIsbn(String isbn) {

		// Find the book by ISBN
		Optional<Book> book = bookRepository.findByIsbn(isbn);
		if (book.isEmpty()) {
			// Throw an exception if the book is not found
			throw new BookNotFoundException("Book not found with ISBN: " + isbn);
		}

		// Find book reviews by book
		List<BookReview> list = bookReviewRepository.findByIsbn(book.get());
		if (list.isEmpty()) {
			// Throw an exception if there are no book reviews available
			throw new BookReviewNotFoundException("There are no book reviews available with ISBN: " + isbn);
		}

		// Create a list of BookReviewDto objects
		List<BookReviewDto> dtos = new ArrayList<>();

		// Copy properties from entities to DTOs
		for (BookReview review : list) {
			BookReviewDto dto = new BookReviewDto();
			BeanUtils.copyProperties(review, dto);
			dto.setIsbn(review.getIsbn().getIsbn());
			dto.setReviewerID(review.getReviewerId().getreviewerId());
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public List<BookReviewDto> updateRatingByIsbn(String isbn, BookReviewDto bookReviewDto) {

		// Find the book by ISBN
		Optional<Book> book = bookRepository.findByIsbn(isbn);
		if (book.isEmpty()) {
			// Throw an exception if the book is not found
			throw new BookNotFoundException("Book not found with ISBN: " + isbn);
		}

		// Find book reviews by book
		List<BookReview> list = bookReviewRepository.findByIsbn(book.get());
		if (list.isEmpty()) {
			// Throw an exception if there are no book reviews available
			throw new BookReviewNotFoundException("There are no book reviews available with ISBN: " + isbn);
		}

		// Create a list of BookReviewDto objects
		List<BookReviewDto> dtos = new ArrayList<>();

		// Update rating and save book reviews
		for (BookReview review : list) {
			review.setRating(bookReviewDto.getRating());
			bookReviewRepository.save(review);
			BookReviewDto dto = new BookReviewDto();
			BeanUtils.copyProperties(review, dto);
			dto.setIsbn(review.getIsbn().getIsbn());
			dto.setReviewerID(review.getReviewerId().getreviewerId());
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public List<BookReviewDto> updateCommentsByIsbn(String isbn, BookReviewDto bookReviewDto) {

		// Find the book by ISBN
		Optional<Book> book = bookRepository.findByIsbn(isbn);
		if (book.isEmpty()) {
			// Throw an exception if the book is not found
			throw new BookNotFoundException("Book not found with ISBN: " + isbn);
		}

		// Find book reviews by book
		List<BookReview> list = bookReviewRepository.findByIsbn(book.get());
		if (list.isEmpty()) {
			// Throw an exception if there are no book reviews available
			throw new BookReviewNotFoundException("There are no book reviews available with ISBN: " + isbn);
		}

		// Create a list of BookReviewDto objects
		List<BookReviewDto> dtos = new ArrayList<>();

		// Update comments and save book reviews
		for (BookReview review : list) {
			review.setComments(bookReviewDto.getComments());
			bookReviewRepository.save(review);
			BookReviewDto dto = new BookReviewDto();
			BeanUtils.copyProperties(review, dto);
			dto.setIsbn(review.getIsbn().getIsbn());
			dto.setReviewerID(review.getReviewerId().getreviewerId());
			dtos.add(dto);
		}

		return dtos;
	}
}
