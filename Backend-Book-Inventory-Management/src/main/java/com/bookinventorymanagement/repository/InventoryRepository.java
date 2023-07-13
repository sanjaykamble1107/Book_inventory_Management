package com.bookinventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookinventorymanagement.entity.Inventory;

public interface InventoryRepository  extends JpaRepository<Inventory,Integer>{

}
