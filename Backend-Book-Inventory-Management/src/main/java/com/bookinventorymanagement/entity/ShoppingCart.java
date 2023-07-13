//
//package com.bookinventorymanagement.entity;
// 
//
//import java.util.Objects;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.MapsId;
//import jakarta.persistence.Table;
// 
//@Entity
//@Table(name = "shoppingcart")
// 
//public class ShoppingCart {
//    @Id
//    private Integer userId;
//
//
//    
//    @ManyToOne
//    @JoinColumn(name = "userID")
//    @MapsId
//    private User userID;
//
//
//
//    @ManyToOne
//    @JoinColumn(name = "ISBN",columnDefinition = "char(13)")
//    private Book isbn;
//
//    public ShoppingCart() {
//        super();
//
//    }
// 
//    public ShoppingCart(User userID, Book isbn) {
//        super();
//        this.userID = userID;
//        this.isbn = isbn;
//    }
// 
//    public User getuserID() {
//        return userID;
//    }
// 
//    public void setuserID(User userID) {
//        this.userID = userID;
//    }
// 
//    public Book getIsbn() {
//        return isbn;
//    }
// 
//    public void setIsbn(Book isbn) {
//        this.isbn = isbn;
//    }
// 
//    @Override
//    public int hashCode() {
//        return Objects.hash(isbn, userID);
//    }
// 
//    @Override
//    public String toString() {
//        return "ShoppingCart [userID=" + userID + ", isbn=" + isbn + "]";
//    }
// 
//    
// 
//    
// 
//
//}
//
