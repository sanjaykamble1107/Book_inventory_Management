package com.bookinventorymanagement.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserID",nullable = false)
	private Integer userId;
	@Column(name="LastName",nullable = false)
	private String lastName;
	@Column(name="FirstName",nullable = false)
	private String firstName;
	@Column(name = "PhoneNumber",columnDefinition = "char(14)")
	private String phoneNumber;
	@Column(name="UserName",nullable = false)
	private String userName;
	@Column(name="Password",nullable = false)
	private String password;
	
	
	@ManyToOne
	@JoinColumn(name = "RoleNumber")
	private PermRole roleNumber;

	public User() {
		super();
	}

	public User(Integer userId, String lastName, String firstName, String phoneNumber, String userName, String password,
			PermRole roleNumber) {
		super();
		this.userId = userId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
		this.roleNumber = roleNumber;
	}

	public Integer getuserId() {
		return userId;
	}

	public void setuserId(Integer userId) {
		this.userId = userId;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getphoneNumber() {
		return phoneNumber;
	}

	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public PermRole getroleNumber() {
		return roleNumber;
	}

	public void setroleNumber(PermRole roleNumber) {
		this.roleNumber = roleNumber;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", lastName=" + lastName + ", firstName=" + firstName + ", phoneNumber="
				+ phoneNumber + ", userName=" + userName + ", password=" + password + ", roleNumber=" + roleNumber
				+ "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(roleNumber.getPermRole()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {	
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
