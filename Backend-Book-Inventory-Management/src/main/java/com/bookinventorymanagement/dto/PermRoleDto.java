package com.bookinventorymanagement.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Validated
public class PermRoleDto {

	@Column(nullable = false)
	@NotNull(message = "RoleNumber is required")
	private Integer roleNumber;
	@Column(length = 30)
	@NotNull(message = "PermRole is required")
	@Size(max = 30, message = "PermRole should not exceed 30 characters")
	private String permRole;

	public PermRoleDto() {
		super();
	}

	public PermRoleDto(Integer roleNumber, String permRole) {
		super();
		this.roleNumber = roleNumber;
		this.permRole = permRole;
	}

	public Integer getRoleNumber() {
		return roleNumber;
	}

	public void setRoleNumber(Integer roleNumber) {
		this.roleNumber = roleNumber;
	}

	public String getPermRole() {
		return permRole;
	}

	public void setPermRole(String permRole) {
		this.permRole = permRole;
	}

	@Override
	public String toString() {
		return "PermRoleDto [roleNumber=" + roleNumber + ", permRole=" + permRole + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(permRole, roleNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermRoleDto other = (PermRoleDto) obj;
		return Objects.equals(permRole, other.permRole) && Objects.equals(roleNumber, other.roleNumber);
	}

}
