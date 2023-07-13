package com.bookinventorymanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookinventorymanagement.entity.Author;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
	
	Optional<Author> findByFirstName(String firstName);

	Optional<Author> findByLastName(String lastName);
	
	

}
