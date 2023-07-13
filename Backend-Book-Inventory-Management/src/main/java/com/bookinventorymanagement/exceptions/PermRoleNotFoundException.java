package com.bookinventorymanagement.exceptions;

public class PermRoleNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PermRoleNotFoundException() {
		super();
		
	}

	public PermRoleNotFoundException(String message) {
		super(message);
		
	}
	

}
