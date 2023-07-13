package com.bookinventorymanagement.entity;

import java.io.Serializable;
import java.util.Objects;

public class CompositeKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Author authorID;

	private Book isbn;

	public CompositeKey() {
		super();
	}

	public CompositeKey(Author authorID, Book isbn) {
		super();
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

	@Override
	public int hashCode() {
		return Objects.hash(authorID, isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeKey other = (CompositeKey) obj;
		return Objects.equals(authorID, other.authorID) && Objects.equals(isbn, other.isbn);
	}

}
