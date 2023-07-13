package com.bookinventorymanagement.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Validated
public class CategoryDto {
	@Column(nullable = false)
	@NotNull(message = "CatID is required")
	private Integer catId;
	@Column(length = 24)
	@Size(max = 24, message = "CatDescription should not exceed 24 characters")
	private String catDescription;

	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryDto(Integer catId, String catDescription) {
		super();
		this.catId = catId;
		this.catDescription = catDescription;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatDescription() {
		return catDescription;
	}

	public void setCatDescription(String catDescription) {
		this.catDescription = catDescription;
	}

	@Override
	public String toString() {
		return "CategoryDto [catId=" + catId + ", catDescription=" + catDescription + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(catDescription, catId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDto other = (CategoryDto) obj;
		return Objects.equals(catDescription, other.catDescription) && Objects.equals(catId, other.catId);
	}

}
