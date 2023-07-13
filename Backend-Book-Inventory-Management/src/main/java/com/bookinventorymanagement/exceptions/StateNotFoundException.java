package com.bookinventorymanagement.exceptions;

public class StateNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public StateNotFoundException() {
		super();
		
	}

	public StateNotFoundException(String message) {
		super(message);
	
	}
	

}
