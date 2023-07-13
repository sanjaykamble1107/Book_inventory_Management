package com.bookinventorymanagement.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Validated
public class ShoppingCartDto {
	
	@Column(nullable = false)
	@NotNull(message = "UserID is required")
	private Integer userId;
	@Column(nullable = false, length = 13)
	@NotBlank(message = "ISBN is required")
	@Size(min = 13, max = 13, message = "ISBN should be exactly 13 characters long")
	private String isbn;

	public ShoppingCartDto() {
		super();
	}

	public ShoppingCartDto(Integer userId, String isbn) {
		super();
		this.userId = userId;
		this.isbn = isbn;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "ShoppingCartDto [userId=" + userId + ", isbn=" + isbn + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCartDto other = (ShoppingCartDto) obj;
		return Objects.equals(isbn, other.isbn) && Objects.equals(userId, other.userId);
	}

}
