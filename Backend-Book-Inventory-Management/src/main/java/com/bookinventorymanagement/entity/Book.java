package com.bookinventorymanagement.entity;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name="ISBN", columnDefinition = "char(13)", nullable=false)
    private String isbn;
    @Column(name="Title", nullable = false)
    private String title;
    @Column(name="Description")
    private String description;
    @Column(name="Edition", columnDefinition = "char(30)")
    private String edition;
    
    
    @ManyToOne
    @JoinColumn(name = "Category")
    private Category catId;
    
    @ManyToOne
    @JoinColumn(name = "PublisherId")
    private Publisher publisherId;
    
    public Book(String isbn, String title, String description, String edition, Publisher publisherId, Category catId) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.edition = edition;
        this.publisherId = publisherId;
        this.catId = catId;
    }
    
    
    
    public Book() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getEdition() {
        return edition;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }
    public Publisher getPublisherId() {
        return publisherId;
    }
    public void setPublisherId(Publisher publisherId) {
        this.publisherId = publisherId;
    }
    public Category getCatId() {
        return catId;
    }
    public void setCatId(Category catId) {
        this.catId = catId;
    }
    @Override
    public String toString() {
        return "Book [isbn=" + isbn + ", title=" + title + ", description=" + description + ", edition=" + edition
                + ", publisherId=" + publisherId + ", catId=" + catId + "]";
    }
    
    
}    

