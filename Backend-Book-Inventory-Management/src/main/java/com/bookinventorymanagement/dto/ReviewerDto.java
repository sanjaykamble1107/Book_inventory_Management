package com.bookinventorymanagement.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Validated
public class ReviewerDto {
	
	
	@NotNull(message = "ReviewerID is required")
	private Integer reviewerId;
	
	@NotBlank(message = "Name is required")
	@Size(max = 20, message = "Name should not exceed 20 characters")
	private String name;

	@Size(max = 30, message = "EmployedBy should not exceed 30 characters")
	private String employedBy;

	public ReviewerDto() {
		super();
		
	}

	public ReviewerDto(Integer reviewerId, String name, String employedBy) {
		super();
		this.reviewerId = reviewerId;
		this.name = name;
		this.employedBy = employedBy;
	}

	public Integer getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(Integer reviewerId) {
		this.reviewerId = reviewerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployedBy() {
		return employedBy;
	}

	public void setEmployedBy(String employedBy) {
		this.employedBy = employedBy;
	}

	@Override
	public String toString() {
		return "ReviewerDto [reviewerId=" + reviewerId + ", name=" + name + ", employedBy=" + employedBy + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(employedBy, name, reviewerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewerDto other = (ReviewerDto) obj;
		return Objects.equals(employedBy, other.employedBy) && Objects.equals(name, other.name)
				&& Objects.equals(reviewerId, other.reviewerId);
	}

}
