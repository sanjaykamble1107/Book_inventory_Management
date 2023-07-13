package com.bookinventorymanagement.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Validated
public class BookReviewDto {
	@Column(nullable = false, length = 13)
	@NotBlank(message = "ISBN is required")
	@Size(min = 13, max = 13, message = "ISBN should be exactly 13 characters long")
	private String isbn;
	@Column(nullable = false)
	@NotNull(message = "Reviewer ID is required")
	private Integer reviewerID;
	@NotNull(message="Rating is required")
	private Integer rating;
	@Column(length = 255)
	@Size(max = 255, message = "Comments should not exceed 255 characters")
	private String comments;

	public BookReviewDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookReviewDto(String isbn, Integer reviewerID, Integer rating, String comments) {
		super();
		this.isbn = isbn;
		this.reviewerID = reviewerID;
		this.rating = rating;
		this.comments = comments;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getReviewerID() {
		return reviewerID;
	}

	public void setReviewerID(Integer reviewerID) {
		this.reviewerID = reviewerID;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "BookReviewDto [isbn=" + isbn + ", reviewerID=" + reviewerID + ", rating=" + rating + ", comments="
				+ comments + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(comments, isbn, rating, reviewerID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookReviewDto other = (BookReviewDto) obj;
		return Objects.equals(comments, other.comments) && Objects.equals(isbn, other.isbn)
				&& Objects.equals(rating, other.rating) && Objects.equals(reviewerID, other.reviewerID);
	}

}
