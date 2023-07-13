package com.bookinventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookinventorymanagement.entity.BookCondition;

public interface BookConditionRepository extends JpaRepository<BookCondition, Integer>{

}
