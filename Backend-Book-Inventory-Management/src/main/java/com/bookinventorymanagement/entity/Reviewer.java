package com.bookinventorymanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

@Entity
@Table(name = "reviewer")
public class Reviewer {
	@Id
	@TableGenerator(initialValue = 30,name = "ReviewerID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ReviewerID", nullable = false)
	private Integer reviewerId;
	@Column(name = "Name", nullable = false)
	private String name;
	@Column(name = "EmployedBy", columnDefinition = "char(30)")
	private String employedBy;

	public Reviewer() {
		super();
	}

	public Reviewer(Integer reviewerId, String name, String employedBy) {
		super();
		this.reviewerId = reviewerId;
		this.name = name;
		this.employedBy = employedBy;
	}

	public Integer getreviewerId() {
		return reviewerId;
	}

	public void setreviewerId(Integer reviewerId) {
		this.reviewerId = reviewerId;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getemployedBy() {
		return employedBy;
	}

	public void setemployedBy(String employedBy) {
		this.employedBy = employedBy;
	}

	@Override
	public String toString() {
		return "Reviewer [reviewerId=" + reviewerId + ", name=" + name + ", employedBy=" + employedBy + "]";
	}

}
