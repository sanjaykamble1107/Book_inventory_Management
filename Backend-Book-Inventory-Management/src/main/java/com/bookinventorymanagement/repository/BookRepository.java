
package com.bookinventorymanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookinventorymanagement.entity.Book;
import com.bookinventorymanagement.entity.Category;
import com.bookinventorymanagement.entity.Publisher;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Optional<Book> findByIsbn(String isbn);

	Optional<Book> findByTitle(String title);

	Optional<List<Book>> findByCatId(Category category);

	Optional<List<Book>> findByPublisherId(Publisher publisher);
	
	

}
