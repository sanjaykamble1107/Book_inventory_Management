package com.bookinventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookinventorymanagement.entity.PurchaseLog;

public interface PurchaseLogRepository extends JpaRepository<PurchaseLog,Integer> {
	
	

}
