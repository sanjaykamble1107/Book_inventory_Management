package com.bookinventorymanagement.entity;
 
import java.math.BigDecimal;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
 
@Entity
@Table(name = "bookcondition")
public class BookCondition {
    @Id
    @TableGenerator(initialValue = 7,name = "Ranks")
   	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ranks", nullable = false)
    private Integer ranks;
    @Column(name = "Description")
    private String description;
    @Column(name = "FullDescription")
    private String fullDescription;
    @Column(name = "Price", length = 12, precision = 2)
    private BigDecimal price;
    
    
    
 
    public BookCondition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookCondition(Integer ranks, String description, String fullDescription, BigDecimal price) {
        super();
        this.ranks = ranks;
        this.description = description;
        this.fullDescription = fullDescription;
        this.price = price;
    }
 
    public Integer getRanks() {
        return ranks;
    }
 
    public void setRanks(Integer ranks) {
        this.ranks = ranks;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getFullDescription() {
        return fullDescription;
    }
 
    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }
 
    public BigDecimal getPrice() {
        return price;
    }
 
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
 
    @Override
    public String toString() {
        return "BookCondition [ranks=" + ranks + ", description=" + description + ", fullDescription=" + fullDescription
                + ", price=" + price + "]";
    }
 
}

