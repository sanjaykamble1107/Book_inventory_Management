package com.bookinventorymanagement.service;

import com.bookinventorymanagement.dto.InventoryDto;
import com.bookinventorymanagement.dto.ResponseDto;

public interface InventoryService {
	
	public ResponseDto saveInventory(InventoryDto dto);
	
	public InventoryDto getInventoryById(Integer inventoryId);
	
	public InventoryDto updatePurchasedById(Integer inventoryId,InventoryDto inventoryDto);

}
