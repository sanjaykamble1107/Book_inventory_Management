package com.bookinventorymanagement.dto;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Validated
public class BookConditionDto {
	@NotNull(message="ranks cannot be null it is required")
	private Integer ranks;
	@NotBlank(message="Description is required")
	@Size(max=50 ,message="Description must be maximum of 50 characters")
	private String description;
	@NotBlank(message="Fulldescription is required")
	@Size(max=255 ,message="Fulldescription must be maximum of 255 characters")
	private String fullDescription;
	@DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than or equal to 0.01")
    @DecimalMax(value = "99999999.99", inclusive = true, message = "Price must be less than or equal to 99999999.99")
    @Digits(integer = 12, fraction = 2, message = "Price must have up to 12 digits in total, with 2 decimal places")
	private BigDecimal price = new BigDecimal("30.00");

	public BookConditionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookConditionDto(Integer ranks, String description, String fullDescription, BigDecimal price) {
		super();
		this.ranks = ranks;
		this.description = description;
		this.fullDescription = fullDescription;
		this.price = price;
	}

	public Integer getRanks() {
		return ranks;
	}

	public void setRanks(Integer ranks) {
		this.ranks = ranks;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "BookConditionDto [ranks=" + ranks + ", description=" + description + ", fullDescription="
				+ fullDescription + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, fullDescription, price, ranks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookConditionDto other = (BookConditionDto) obj;
		return Objects.equals(description, other.description) && Objects.equals(fullDescription, other.fullDescription)
				&& Objects.equals(price, other.price) && Objects.equals(ranks, other.ranks);
	}

}
