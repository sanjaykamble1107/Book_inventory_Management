package com.bookinventorymanagement.entity;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
 
@Entity
@Table(name="inventory")
public class Inventory {
    @Id
    @TableGenerator(initialValue = 1000065,name = "InventoryID")
   	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="InventoryID", nullable = false)
        private Integer inventoryId;
    
    @Column(name="Purchased", nullable = false,columnDefinition = "bit(1)")
    private byte purchased;
    
    @ManyToOne
    @JoinColumn(name = "ISBN", nullable = false)
        private Book isbn;
    
    @ManyToOne
    @JoinColumn(name = "Ranks", nullable = false)
        private BookCondition ranks;
    
    
    public Inventory() {
		super();
		
	}
	public Inventory(Integer inventoryId, Book isbn, BookCondition ranks, byte purchased) {
        super();
        this.inventoryId = inventoryId;
        this.isbn = isbn;
        this.ranks = ranks;
        this.purchased = purchased;
    }
    public Integer getInventoryId() {
        return inventoryId;
    }
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }
    public Book getIsbn() {
        return isbn;
    }
    public void setIsbn(Book isbn) {
        this.isbn = isbn;
    }
    public BookCondition getRanks() {
        return ranks;
    }
    public void setRanks(BookCondition ranks) {
        this.ranks = ranks;
    }
    public byte getPurchased() {
        return purchased;
    }
    public void setPurchased(byte purchased) {
        this.purchased = purchased;
    }
    @Override
    public String toString() {
        return "Inventory [inventoryId=" + inventoryId + ", isbn=" + isbn + ", ranks=" + ranks + ", purchased="
                + purchased + "]";
    }
    
    
}

