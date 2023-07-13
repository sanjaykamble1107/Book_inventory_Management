package com.bookinventorymanagement.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Validated
public class BookDto {
	
	@NotBlank(message = "ISBN is required")
	@Size(min = 13, max = 13, message = "ISBN should be exactly 13 characters long")
	private String isbn;
	@Column(nullable = false, length = 70)
	@NotBlank(message = "Title is required")
	@Size(max = 70, message = "Title should not exceed 70 characters")
	private String title;
	@Column(length = 100)
	@NotNull(message = "Description is required")
	@Size(max = 100, message = "Description should not exceed 100 characters")
	private String description;
	@NotNull(message = "Category is required")
	private Integer category;
	@Column(length = 30)
	@Size(max = 30, message = "Edition should not exceed 30 characters")
	private String edition;
	@Column(nullable = false)
	@NotNull(message = "Publisher ID is required")
	private Integer publisherId;

	public BookDto() {
		super();
	}

	public BookDto(String isbn, String title, String description, Integer category, String edition,
			Integer publisherId) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.description = description;
		this.category = category;
		this.edition = edition;
		this.publisherId = publisherId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	@Override
	public String toString() {
		return "BookDto [isbn=" + isbn + ", title=" + title + ", description=" + description + ", category=" + category
				+ ", edition=" + edition + ", publisherId=" + publisherId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, description, edition, isbn, publisherId, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookDto other = (BookDto) obj;
		return Objects.equals(category, other.category) && Objects.equals(description, other.description)
				&& Objects.equals(edition, other.edition) && Objects.equals(isbn, other.isbn)
				&& Objects.equals(publisherId, other.publisherId) && Objects.equals(title, other.title);
	}

}
