package com.bookinventorymanagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.dto.PurchaseLogDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.entity.Inventory;
import com.bookinventorymanagement.entity.PurchaseLog;
import com.bookinventorymanagement.entity.User;
import com.bookinventorymanagement.exceptions.InventoryNotFoundException;
import com.bookinventorymanagement.exceptions.PurchaseLogNotFoundException;
import com.bookinventorymanagement.exceptions.UserNotFoundException;
import com.bookinventorymanagement.repository.InventoryRepository;
import com.bookinventorymanagement.repository.PurchaseLogRepository;
import com.bookinventorymanagement.repository.UserRepository;
import com.bookinventorymanagement.service.PurchaseLogService;

@Service
public class PurchaseLogServiceImpl implements PurchaseLogService {

	@Autowired
	private PurchaseLogRepository purchaseLogRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InventoryRepository inventoryRepository;

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Save a purchase log.
	 *
	 * @param purchaseLogDto The PurchaseLogDto containing the purchase log details to be saved.
	 * @return The ResponseDto with a success message.
	 * @throws PurchaseLogNotFoundException if a purchase log with the same user ID already exists.
	 * @throws UserNotFoundException        if the user with the given user ID is not found.
	 * @throws InventoryNotFoundException  if the inventory with the given inventory ID is not found.
	 */
	@Override
	public ResponseDto savePurchaseLog(PurchaseLogDto purchaseLogDto) {
		Optional<PurchaseLog> optionalLog = purchaseLogRepository.findById(purchaseLogDto.getUserId());
		if (optionalLog.isPresent()) {
			throw new PurchaseLogNotFoundException(
					"Purchase log is already present with ID: " + purchaseLogDto.getUserId());
		}

		PurchaseLog purchaseLog = new PurchaseLog();

		Optional<User> user = userRepository.findById(purchaseLogDto.getUserId());
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found with ID: " + purchaseLogDto.getUserId());
		}

		Optional<Inventory> optional = inventoryRepository.findById(purchaseLogDto.getInventoryId());
		if (optional.isEmpty()) {
			throw new InventoryNotFoundException("Inventory not found with ID: " + purchaseLogDto.getInventoryId());
		}

		purchaseLog.setUserId(user.get());
		purchaseLog.setInventoryId(optional.get());
		purchaseLogRepository.save(purchaseLog);

		responseDto.setResponseMessage("Purchase Log added successfully");

		Inventory inventory = optional.get();
		inventory.setPurchased((byte) (inventory.getPurchased() + 1));
		inventoryRepository.save(inventory);

		return responseDto;
	}

	/**
	 * Get a purchase log by user ID.
	 *
	 * @param userId The ID of the user.
	 * @return The PurchaseLogDto containing the purchase log details.
	 * @throws PurchaseLogNotFoundException if the purchase log with the given user ID is not found.
	 */
	@Override
	public PurchaseLogDto getPurchaseLogByUserId(Integer userId) {
		Optional<PurchaseLog> optionalPurchaseLog = purchaseLogRepository.findById(userId);
		if (optionalPurchaseLog.isPresent()) {
			PurchaseLog purchaseLog = purchaseLogRepository.getById(userId);
			PurchaseLogDto dto = new PurchaseLogDto();
			dto.setUserId(purchaseLog.getUserId().getuserId());
			dto.setInventoryId(purchaseLog.getInventoryId().getInventoryId());
			return dto;
		}
		throw new PurchaseLogNotFoundException("PurchaseLog not found with ID: " + userId);
	}

	/**
	 * Update the inventory ID of a purchase log by user ID.
	 *
	 * @param userId         The ID of the user.
	 * @param purchaseLogDto The PurchaseLogDto containing the updated purchase log details.
	 * @return The PurchaseLogDto containing the updated purchase log details.
	 * @throws PurchaseLogNotFoundException if the purchase log with the given user ID is not found.
	 */
	@Override
	public PurchaseLogDto updateInventoryIdByUserId(Integer userId, PurchaseLogDto purchaseLogDto) {
		Optional<PurchaseLog> optionalPurchaseLog = purchaseLogRepository.findById(userId);
		if (optionalPurchaseLog.isPresent()) {
			PurchaseLog purchaseLog = optionalPurchaseLog.get();

			Inventory oldInventory = purchaseLog.getInventoryId();
			Inventory newInventory = inventoryRepository.getById(purchaseLogDto.getInventoryId());

			oldInventory.setPurchased((byte) 0);
			newInventory.setPurchased((byte) 1);

			inventoryRepository.save(oldInventory);
			inventoryRepository.save(newInventory);

			purchaseLog.setInventoryId(newInventory);
			purchaseLogRepository.save(purchaseLog);

			PurchaseLogDto dto = new PurchaseLogDto();
			dto.setInventoryId(purchaseLog.getInventoryId().getInventoryId());
			dto.setUserId(purchaseLog.getUserId().getuserId());
			return dto;
		}
		throw new PurchaseLogNotFoundException("PurchaseLog not found with ID: " + userId);
	}
}

