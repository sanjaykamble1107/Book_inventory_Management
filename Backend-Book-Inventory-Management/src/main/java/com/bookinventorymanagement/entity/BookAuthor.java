package com.bookinventorymanagement.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookauthor")
@IdClass(CompositeKey.class)
public class BookAuthor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "PrimaryAuthor", columnDefinition = "char(1)")
	private String primaryAuthor;

	@Id
	@ManyToOne
	@JoinColumn(name = "AuthorID", nullable = false)
	private Author authorID;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "ISBN", nullable = false, columnDefinition = "char(13)")
	private Book isbn;

	
	
	
	public BookAuthor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public BookAuthor(String primaryAuthor, Author authorID, Book isbn) {
		super();
		this.primaryAuthor = primaryAuthor;
		this.authorID = authorID;
		this.isbn = isbn;
	}



	public Author getAuthorID() {
		return authorID;
	}

	public void setAuthorID(Author authorID) {
		this.authorID = authorID;
	}

	public Book getIsbn() {
		return isbn;
	}

	public void setIsbn(Book isbn) {
		this.isbn = isbn;
	}

	public String getPrimaryAuthor() {

		return primaryAuthor;

	}

	public void setPrimaryAuthor(String primaryAuthor) {

		this.primaryAuthor = primaryAuthor;

	}

}




