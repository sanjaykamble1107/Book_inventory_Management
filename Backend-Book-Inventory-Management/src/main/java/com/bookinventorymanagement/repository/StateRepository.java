package com.bookinventorymanagement.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.bookinventorymanagement.entity.State;

public interface StateRepository extends JpaRepository<State, String>{
	
	

}
