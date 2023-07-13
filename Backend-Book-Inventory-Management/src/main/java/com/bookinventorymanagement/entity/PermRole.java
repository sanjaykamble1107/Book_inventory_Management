package com.bookinventorymanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "permrole")
public class PermRole {
	
	@Id
	@Column(name = "RoleNumber",nullable = false)
	private Integer rollNumber;
	
	@Column(name = "PermRole")
	private String permRole;

	
	
	public PermRole() {
		super();
	}

	public PermRole(Integer rollNumber, String permRole) {
		super();
		this.rollNumber = rollNumber;
		this.permRole = permRole;
	}

	public Integer getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(Integer rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getPermRole() {
		return permRole;
	}

	public void setPermRole(String permRole) {
		this.permRole = permRole;
	}

	@Override
	public String toString() {
		return "PermRole [rollNumber=" + rollNumber + ", permRole=" + permRole + "]";
	}
	
	

}
