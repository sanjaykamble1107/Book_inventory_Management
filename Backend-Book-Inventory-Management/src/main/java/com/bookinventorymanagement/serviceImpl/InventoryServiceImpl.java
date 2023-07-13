package com.bookinventorymanagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.dto.InventoryDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.entity.Book;
import com.bookinventorymanagement.entity.BookCondition;
import com.bookinventorymanagement.entity.Inventory;
import com.bookinventorymanagement.exceptions.BookConditionNotFoundException;
import com.bookinventorymanagement.exceptions.BookNotFoundException;
import com.bookinventorymanagement.exceptions.InventoryNotFoundException;
import com.bookinventorymanagement.repository.BookConditionRepository;
import com.bookinventorymanagement.repository.BookRepository;
import com.bookinventorymanagement.repository.InventoryRepository;
import com.bookinventorymanagement.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookConditionRepository bookConditionRepository;

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Save a book inventory.
	 *
	 * @param dto The InventoryDto containing the inventory details to be saved.
	 * @return The ResponseDto with a success message.
	 * @throws InventoryNotFoundException if an inventory with the same ID already
	 *                                    exists or if the associated book or book
	 *                                    condition is not found.
	 */
	@Override
	public ResponseDto saveInventory(InventoryDto dto) {
		Optional<Inventory> optionalInventory = inventoryRepository.findById(dto.getInventoryId());
		if (optionalInventory.isPresent()) {
			throw new InventoryNotFoundException(
					"Inventory already present with inventory ID: " + dto.getInventoryId());
		}
		Inventory inventory = new Inventory();
		Optional<Book> optionalBook = bookRepository.findByIsbn(dto.getIsbn());
		if (optionalBook.isEmpty()) {
			throw new InventoryNotFoundException("Book not found with ISBN: " + dto.getIsbn());
		}
		BookCondition bookCondition = bookConditionRepository.getById(dto.getRanks());
		if (bookCondition == null) {
			throw new InventoryNotFoundException("Book condition not found with ranks: " + dto.getRanks());
		}
		inventory.setIsbn(optionalBook.get());
		inventory.setRanks(bookCondition);
		BeanUtils.copyProperties(dto, inventory);
		responseDto.setResponseMessage("Book inventory added successfully");
		inventoryRepository.save(inventory);

		return responseDto;
	}

	/**
	 * Get an inventory by its ID.
	 *
	 * @param inventoryId The ID of the inventory.
	 * @return The InventoryDto containing the inventory details.
	 * @throws InventoryNotFoundException if the inventory with the given ID is not
	 *                                    found.
	 */
	@Override
	public InventoryDto getInventoryById(Integer inventoryId) {
		Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);
		if (optionalInventory.isPresent()) {
			Inventory inventory = inventoryRepository.getById(inventoryId);
			InventoryDto dto = new InventoryDto();
			BeanUtils.copyProperties(inventory, dto);
			dto.setIsbn(inventory.getIsbn().getIsbn());
			dto.setRanks(inventory.getRanks().getRanks());
			return dto;
		}

		throw new InventoryNotFoundException("Inventory not found with ID: " + inventoryId);
	}

	/**
	 * Update the purchased quantity of an inventory by its ID.
	 *
	 * @param inventoryId  The ID of the inventory.
	 * @param inventoryDto The InventoryDto containing the updated inventory
	 *                     details.
	 * @return The InventoryDto containing the updated inventory details.
	 * @throws InventoryNotFoundException if the inventory with the given ID is not
	 *                                    found.
	 */
	@Override
	public InventoryDto updatePurchasedById(Integer inventoryId, InventoryDto inventoryDto) {
		Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);
		if (optionalInventory.isPresent()) {
			Inventory inventory = optionalInventory.get();
			inventory.setPurchased(inventoryDto.getPurchased());
			inventoryRepository.save(inventory);
			InventoryDto dto = new InventoryDto();
			dto.setIsbn(inventory.getIsbn().getIsbn());
			dto.setRanks(inventory.getRanks().getRanks());
			BeanUtils.copyProperties(inventory, dto);
			return dto;
		}

		throw new InventoryNotFoundException("Inventory not found with ID: " + inventoryId);
	}
}
