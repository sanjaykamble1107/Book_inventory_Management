package com.bookinventorymanagement.exceptions;

public class BookReviewNotFoundException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public BookReviewNotFoundException() {
		super();
		
	}

	public BookReviewNotFoundException(String message) {
		super(message);
		
	}
	

}
