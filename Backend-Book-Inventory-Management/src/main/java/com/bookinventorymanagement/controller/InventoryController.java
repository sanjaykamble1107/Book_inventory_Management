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

import com.bookinventorymanagement.dto.InventoryDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.service.InventoryService;

import jakarta.validation.Valid;
@CrossOrigin(origins="http://localhost:4200/")

@RestController
@RequestMapping("/inventory")
@Validated
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	// Add an inventory
	@PostMapping("/add")
	public ResponseEntity<ResponseDto> addInventory(@Valid @RequestBody InventoryDto dto){
		ResponseDto inventoryDto=inventoryService.saveInventory(dto);
		return new ResponseEntity<ResponseDto>(inventoryDto,HttpStatus.OK);
	}
	
	// Get an inventory by ID
	@GetMapping("/{inventoryId}")
	public ResponseEntity<InventoryDto> getInventoryById(@PathVariable Integer inventoryId){
		InventoryDto inventoryDto=inventoryService.getInventoryById(inventoryId);
		return new ResponseEntity<InventoryDto>(inventoryDto,HttpStatus.OK);
	}
	
	// Update the purchased quantity of an inventory by ID
	@PutMapping("/update/{inventoryId}")
	public ResponseEntity<InventoryDto> updatePurchasedById(@PathVariable Integer inventoryId, @RequestBody InventoryDto dto){
		InventoryDto inventoryDto=inventoryService.updatePurchasedById(inventoryId, dto);
		return new ResponseEntity<InventoryDto>(inventoryDto,HttpStatus.OK);
	}
}
