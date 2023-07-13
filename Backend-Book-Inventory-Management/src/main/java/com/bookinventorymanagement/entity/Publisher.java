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
@Table(name = "publisher")
public class Publisher {


	@Id
	@TableGenerator(initialValue = 14,name = "PublisherID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PublisherID",nullable = false)
	private Integer publisherId;
	@Column(name = "Name",nullable = false)
	private String name;
	@Column(name = "City",nullable = true)
	private String city;
	
	@ManyToOne
	@JoinColumn(name = "StateCode",columnDefinition = "char(2)",nullable = true)
	private State stateCode;

	
	public Publisher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Publisher(Integer publisherId, String name, String city, State state) {
		super();
		this.publisherId = publisherId;
		this.name = name;
		this.city = city;
		this.stateCode = state;
	}

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public State getState() {
		return stateCode;
	}

	public void setState(State state) {
		this.stateCode = state;
	}

	@Override
	public String toString() {
		return "Publisher [publisherId=" + publisherId + ", name=" + name + ", city=" + city + ", state=" + stateCode + "]";
	}
	
	
	

	
}
