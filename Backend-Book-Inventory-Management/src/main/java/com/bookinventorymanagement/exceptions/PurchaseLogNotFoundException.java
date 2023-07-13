package com.bookinventorymanagement.exceptions;

public class PurchaseLogNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PurchaseLogNotFoundException() {
		super();
		
	}

	public PurchaseLogNotFoundException(String message) {
		super(message);
		
	}
	

}
