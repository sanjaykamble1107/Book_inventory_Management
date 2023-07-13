package com.bookinventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookinventorymanagement.entity.Reviewer;

public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {

}
