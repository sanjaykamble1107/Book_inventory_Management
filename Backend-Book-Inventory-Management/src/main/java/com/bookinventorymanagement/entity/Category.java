package com.bookinventorymanagement.entity;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
 
@Entity
@Table(name = "category")
public class Category {
    @Id
    @TableGenerator(initialValue = 10,name = "CatID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CatID", nullable = false)
    private Integer catId;
    
    @Column(name = "CatDescription")
    private String catDescription;
 
    public Category(Integer catId, String catDescription) {
        super();
        this.catId = catId;
        this.catDescription = catDescription;
    }
 
    
    public Category() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getCatId() {
        return catId;
    }
 
    public void setCatId(Integer catId) {
        this.catId = catId;
    }
 
    public String getCatDescription() {
        return catDescription;
    }
 
    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }
 
    @Override
    public String toString() {
        return "Category [catId=" + catId + ", catDescription=" + catDescription + "]";
    }
 
}

