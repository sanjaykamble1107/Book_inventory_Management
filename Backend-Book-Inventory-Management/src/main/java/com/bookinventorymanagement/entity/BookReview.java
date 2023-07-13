package com.bookinventorymanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookreview")
@IdClass(CompositeBookReview.class)
public class BookReview {

	@Column(name = "Rating")
	private Integer rating;
	@Column(name = "Comments")
	private String comments;

	@Id
	@ManyToOne
	@JoinColumn(name = "ISBN")
	private Book isbn;

	@Id
	@ManyToOne
	@JoinColumn(name = "ReviewerID")
	private Reviewer reviewerId;

	public BookReview() {
		super();
	}

	public BookReview(Integer rating, String comments, Book isbn, Reviewer reviewerId) {
		super();
		this.rating = rating;
		this.comments = comments;
		this.isbn = isbn;
		this.reviewerId = reviewerId;
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

	public Book getIsbn() {
		return isbn;
	}

	public void setIsbn(Book isbn) {
		this.isbn = isbn;
	}

	public Reviewer getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(Reviewer reviewerId) {
		this.reviewerId = reviewerId;
	}

	@Override
	public String toString() {
		return "BookReview [rating=" + rating + ", comments=" + comments + ", isbn=" + isbn + ", reviewerId="
				+ reviewerId + "]";
	}

}
