package com.bookinventorymanagement.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Validated
public class InventoryDto {
	@NotNull(message = "InventoryID is required")
	private Integer inventoryId;
	@Column(nullable = false)
	@NotNull(message = "Purchased is required")
	private Byte purchased;
	@Column(nullable = false, length = 13)
	@NotBlank(message = "ISBN is required")
	@Size(min = 13, max = 13, message = "ISBN should be exactly 13 characters long")
	private String isbn;
	@Column(nullable = false)
	@NotNull(message = "Ranks is required")
	private Integer ranks;

	public InventoryDto() {
		super();

	}

	public InventoryDto(Integer inventoryId, Byte purchased, String isbn, Integer ranks) {
		super();
		this.inventoryId = inventoryId;
		this.purchased = purchased;
		this.isbn = isbn;
		this.ranks = ranks;
	}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Byte getPurchased() {
		return purchased;
	}

	public void setPurchased(Byte purchased) {
		this.purchased = purchased;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getRanks() {
		return ranks;
	}

	public void setRanks(Integer ranks) {
		this.ranks = ranks;
	}

	@Override
	public String toString() {
		return "InventoryDto [inventoryId=" + inventoryId + ", purchased=" + purchased + ", isbn=" + isbn + ", ranks="
				+ ranks + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(inventoryId, isbn, purchased, ranks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryDto other = (InventoryDto) obj;
		return Objects.equals(inventoryId, other.inventoryId) && Objects.equals(isbn, other.isbn)
				&& Objects.equals(purchased, other.purchased) && Objects.equals(ranks, other.ranks);
	}
	
	

}
