package com.bookinventorymanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;

@Entity

public class Author {

	@Id
	@TableGenerator(initialValue = 28,name = "authorID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorID", nullable = false)
    private Integer authorId;
    @Column(name = "FirstName", nullable = false)
    private String firstName;
    @Column(name = "LastName", nullable = false)
    private String lastName;
    @Column(name = "Photo",nullable = true)
    private String photo;
 
    
    public Author() {
		super();
	}

	public Author(int authorId, String firstName, String lastName, String photo) {
		super();
		this.authorId = authorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.photo = photo;
	}

	public int getAuthorId() {
        return authorId;
    }
 
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getPhoto() {
        return photo;
    }
 
    public void setPhoto(String photo) {
        this.photo = photo;
    }

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", firstName=" + firstName + ", lastName=" + lastName + ", photo="
				+ photo + "]";
	}

    

}
