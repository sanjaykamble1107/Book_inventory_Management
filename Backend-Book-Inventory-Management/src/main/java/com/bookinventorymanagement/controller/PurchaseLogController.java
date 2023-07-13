package com.bookinventorymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookinventorymanagement.dto.PurchaseLogDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.service.PurchaseLogService;

import jakarta.validation.Valid;
@CrossOrigin(origins="http://localhost:4200/")

@RestController
@RequestMapping("/purchaselog")
@Validated
public class PurchaseLogController {
	
	@Autowired
	private PurchaseLogService purchaseLogService;
	
	// Save a purchase log
	@PostMapping("/post")
	public ResponseEntity<ResponseDto> savePurchaseLog(@Valid @RequestBody PurchaseLogDto purchaseLogDto){
		ResponseDto purchaselog=purchaseLogService.savePurchaseLog(purchaseLogDto);
		return new ResponseEntity<ResponseDto>(purchaselog,HttpStatus.OK);
	}
	
	// Get a purchase log by user ID
	@GetMapping("/{userId}")
	public ResponseEntity<PurchaseLogDto> getPurchaLogByUserId(@PathVariable Integer userId){
		PurchaseLogDto purchaseLogDto=purchaseLogService.getPurchaseLogByUserId(userId);
		return new ResponseEntity<PurchaseLogDto>(purchaseLogDto,HttpStatus.OK);
	}
	
	// Update the inventory ID of a purchase log by user ID
	@PutMapping("/update/inventoryId/{userId}")
	public ResponseEntity<PurchaseLogDto> updateInventoryIdByUserId(@PathVariable Integer userId,@RequestBody PurchaseLogDto dto){
		PurchaseLogDto purchaseLogDto=purchaseLogService.updateInventoryIdByUserId(userId,dto);
		return new ResponseEntity<PurchaseLogDto>(purchaseLogDto,HttpStatus.OK);
	}
}
