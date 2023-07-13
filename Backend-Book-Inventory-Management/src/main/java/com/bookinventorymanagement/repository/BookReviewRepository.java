package com.bookinventorymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookinventorymanagement.entity.Book;
import com.bookinventorymanagement.entity.BookReview;
import com.bookinventorymanagement.entity.CompositeBookReview;

public interface BookReviewRepository extends JpaRepository<BookReview,CompositeBookReview> {

	public List<BookReview> findByIsbn(Book isbn);

}
