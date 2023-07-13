package com.bookinventorymanagement.entity;

import java.util.Objects;

public class CompositeBookReview{
    
    private Book isbn;
    private Reviewer reviewerId;
    
     
    
    public CompositeBookReview() {
        super();
    }
    public CompositeBookReview(Book isbn, Reviewer reviewerId) {
        super();
        this.isbn = isbn;
        this.reviewerId = reviewerId;
    }
    public Book getIsbn() {
        return isbn;
    }
    public void setIsbn(Book isbn) {
        this.isbn = isbn;
    }
    public Reviewer getReviewerId() {
        return reviewerId;
    }
    public void setReviewerId(Reviewer reviewerId) {
        this.reviewerId = reviewerId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(isbn, reviewerId);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CompositeBookReview other = (CompositeBookReview) obj;
        return Objects.equals(isbn, other.isbn) && Objects.equals(reviewerId, other.reviewerId);
    }
    
    
    
    
    
}
