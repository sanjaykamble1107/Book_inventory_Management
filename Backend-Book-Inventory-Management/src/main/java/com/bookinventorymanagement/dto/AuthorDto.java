package com.bookinventorymanagement.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Validated
public class AuthorDto {
	
	@NotNull(message="AuthorID cannot be Null")
	private Integer authorId;
	@NotNull(message="Please provide the Lastname")
	@Size(min=0,max = 50, message = "Lastname should be minimum of 3 characters and maximum of 50 characters")
	private String lastName;
	@NotNull(message="Please provide the Firstname")
	@Size(min=0,max = 50, message = "Firstname should be  minimum of 3 characters and maximum of 50 characters")
	private String firstName;
	@Size(max = 255, message = "only 1 characters is allowed")
	private String photo;

	public AuthorDto() {
		super();
	}

	public AuthorDto(Integer authorId, String lastName, String firstName, String photo) {
		super();
		this.authorId = authorId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.photo = photo;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "AuthorDto [authorId=" + authorId + ", lastName=" + lastName + ", firstName=" + firstName + ", photo="
				+ photo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorId, firstName, lastName, photo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorDto other = (AuthorDto) obj;
		return Objects.equals(authorId, other.authorId) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(photo, other.photo);
	}
	
}
