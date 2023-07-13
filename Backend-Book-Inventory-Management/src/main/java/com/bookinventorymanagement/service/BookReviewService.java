package com.bookinventorymanagement.service;

import java.util.List;

import com.bookinventorymanagement.dto.BookReviewDto;
import com.bookinventorymanagement.dto.ResponseDto;


public interface BookReviewService {
	public ResponseDto saveBookReview(BookReviewDto bookReviewDto);

	public List<BookReviewDto> getBookReviewByIsbn(String isbn);

	public List<BookReviewDto> updateRatingByIsbn(String isbn, BookReviewDto bookReviewDto);

	public List<BookReviewDto> updateCommentsByIsbn(String isbn, BookReviewDto bookReviewDto);
	


}
