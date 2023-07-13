package com.bookinventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookinventorymanagement.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
