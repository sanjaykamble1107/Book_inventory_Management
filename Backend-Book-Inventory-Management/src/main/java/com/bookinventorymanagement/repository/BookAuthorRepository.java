package com.bookinventorymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookinventorymanagement.entity.Author;
import com.bookinventorymanagement.entity.BookAuthor;
import com.bookinventorymanagement.entity.CompositeKey;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, CompositeKey> {

	List<BookAuthor> findByAuthorID(Author authorId);

}
