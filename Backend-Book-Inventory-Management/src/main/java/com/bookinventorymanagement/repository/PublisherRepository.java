package com.bookinventorymanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookinventorymanagement.entity.Publisher;
import com.bookinventorymanagement.entity.State;

public interface PublisherRepository extends JpaRepository<Publisher, Integer>{

	Optional<Publisher> findByName(String name);

	Optional<List<Publisher>> findByCity(String city);

//	Optional<List<Publisher>> findAllById(String stateCode);

	
}
