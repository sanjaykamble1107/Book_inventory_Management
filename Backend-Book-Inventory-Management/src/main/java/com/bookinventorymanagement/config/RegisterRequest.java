package com.bookinventorymanagement.config;

public class RegisterRequest {

	private String firstname;
	private String lastname;
	private String phoneNumber;
	private String username;
	private String password;
	
	public RegisterRequest() {
		super();
		
	}
	public RegisterRequest(String firstname, String lastname, String phoneNumber, String username, String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "RegisterRequest [firstname=" + firstname + ", lastname=" + lastname + ", phoneNumber=" + phoneNumber
				+ ", username=" + username + ", password=" + password + "]";
	}
	
	
	

	
}
