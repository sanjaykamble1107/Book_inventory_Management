package com.bookinventorymanagement.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
@Validated
public class BookAuthorDto {
	
	@NotBlank(message="Please provide the Isbn")
	@Size(min=13 ,max = 13, message = "Isbn should be minimum of 13 characters")
	private String isbn;
	@NotNull(message="AuthorID cannot be Null")
	private Integer authorId;
	@NotBlank(message="Primary author is required")
	@Size(max = 1, message = "Primary author must be a single character")
	private String primaryAuthor;

	public BookAuthorDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookAuthorDto(String isbn, Integer authorId, String primaryAuthor) {
		super();
		this.isbn = isbn;
		this.authorId = authorId;
		this.primaryAuthor = primaryAuthor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getPrimaryAuthor() {
		return primaryAuthor;
	}

	public void setPrimaryAuthor(String primaryAuthor) {
		this.primaryAuthor = primaryAuthor;
	}

	@Override
	public String toString() {
		return "BookAuthorDto [isbn=" + isbn + ", authorId=" + authorId + ", primaryAuthor=" + primaryAuthor + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorId, isbn, primaryAuthor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookAuthorDto other = (BookAuthorDto) obj;
		return Objects.equals(authorId, other.authorId) && Objects.equals(isbn, other.isbn)
				&& Objects.equals(primaryAuthor, other.primaryAuthor);
	}

}
