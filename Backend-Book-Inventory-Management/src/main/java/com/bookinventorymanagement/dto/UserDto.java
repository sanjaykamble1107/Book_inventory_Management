package com.bookinventorymanagement.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Validated
public class UserDto {
	
	@Column(nullable = false)
	@NotNull(message = "UserID is required")
	private Integer userId;
	@Column(nullable = false, length = 30)
	@NotBlank(message = "LastName is required")
	@Size(max = 30, message = "LastName should not exceed 30 characters")
	private String lastName;
	@Column(nullable = false, length = 20)
	@NotBlank(message = "FirstName is required")
	@Size(max = 20, message = "FirstName should not exceed 20 characters")
	private String firstName;
	@Column(length = 14)
	@Size(max = 14, message = "PhoneNumber should not exceed 14 characters")

	private String phoneNumber;
	@Column(nullable = false, unique = true, length = 30)
	@NotBlank(message = "UserName is required")
	@Size(max = 30, message = "UserName should not exceed 30 characters")
	private String userName;
	@Column(nullable = false, length = 30)
	@NotBlank(message = "Password is required")
	@Size(max = 30, message = "Password should not exceed 30 characters")
	private String password;
	@Column(columnDefinition = "int default 1")
	private Integer roleNumber;
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(Integer userId, String lastName, String firstName, String phoneNumber, String userName,
			String password, Integer roleNumber) {
		super();
		this.userId = userId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
		this.roleNumber = roleNumber;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRoleNumber() {
		return roleNumber;
	}
	public void setRoleNumber(Integer roleNumber) {
		this.roleNumber = roleNumber;
	}
	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", lastName=" + lastName + ", firstName=" + firstName + ", phoneNumber="
				+ phoneNumber + ", userName=" + userName + ", password=" + password + ", roleNumber=" + roleNumber
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, password, phoneNumber, roleNumber, userId, userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(roleNumber, other.roleNumber) && Objects.equals(userId, other.userId)
				&& Objects.equals(userName, other.userName);
	}
	
	

}
