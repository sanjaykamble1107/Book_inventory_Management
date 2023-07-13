package com.bookinventorymanagement.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
@Validated
public class PurchaseLogDto {
	
	@Column(nullable = false)
	@NotNull(message = "UserID is required")
	private Integer userId;
	@Column(nullable = false)
	@NotNull(message = "InventoryID is required")
	private Integer inventoryId;

	public PurchaseLogDto() {
		super();
	}

	public PurchaseLogDto(Integer userId, Integer inventoryId) {
		super();
		this.userId = userId;
		this.inventoryId = inventoryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	@Override
	public String toString() {
		return "PurchaseLogDto [userId=" + userId + ", inventoryId=" + inventoryId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(inventoryId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchaseLogDto other = (PurchaseLogDto) obj;
		return Objects.equals(inventoryId, other.inventoryId) && Objects.equals(userId, other.userId);
	}

}
