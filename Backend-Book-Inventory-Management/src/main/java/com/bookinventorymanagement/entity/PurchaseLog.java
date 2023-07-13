package com.bookinventorymanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchaselog")
public class PurchaseLog {
	
	@Id
	private Integer UserId;
	
	@ManyToOne
	@JoinColumn(name = "UserID")
	@MapsId
	private User userId;
	

	@ManyToOne
	@JoinColumn(name = "InventoryID")
	private Inventory inventoryId;
	
	

	public PurchaseLog() {
		super();
	}

	public PurchaseLog(User userId, Inventory inventoryId) {
		super();
		this.userId = userId;
		this.inventoryId = inventoryId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Inventory getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Inventory inventoryId) {
		this.inventoryId = inventoryId;
	}

	@Override
	public String toString() {
		return "PerchaseLog [userId=" + userId + ", inventoryId=" + inventoryId + "]";
	}
	
	
	

}
