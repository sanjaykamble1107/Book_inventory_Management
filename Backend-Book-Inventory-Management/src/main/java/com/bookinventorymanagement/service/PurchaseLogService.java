package com.bookinventorymanagement.service;

import com.bookinventorymanagement.dto.PurchaseLogDto;
import com.bookinventorymanagement.dto.ResponseDto;

public interface PurchaseLogService {
	
	public ResponseDto savePurchaseLog(PurchaseLogDto purchaseLogDto);
	
	public PurchaseLogDto getPurchaseLogByUserId(Integer userId);

	public PurchaseLogDto updateInventoryIdByUserId(Integer userId,PurchaseLogDto purchaseLogDto);

}
