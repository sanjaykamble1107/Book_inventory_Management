package com.bookinventorymanagement.exceptions;

public class BookConditionNotFoundException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public BookConditionNotFoundException() {
		super();
		
	}

	public BookConditionNotFoundException(String message) {
		super(message);
		
	}
	
	
}
