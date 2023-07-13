package com.bookinventorymanagement.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Validated
public class PublisherDto {
//	@Column(nullable = false)
//	@NotNull(message = "PublisherID is required")
	private Integer publisherId;
	@Column(nullable = false, length = 50)
	@NotBlank(message = "Name is required")
	@Size(max = 50, message = "Name should not exceed 50 characters")
	private String name;
	@Column(length = 30)
	@Size(max = 30, message = "City should not exceed 30 characters")
	private String city;
	@Column(length = 2)
	@Size(max = 2, message = "StateCode should be exactly 2 characters")
	private String stateCode;

	public PublisherDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PublisherDto(Integer publisherId, String name, String city, String stateCode) {
		super();
		this.publisherId = publisherId;
		this.name = name;
		this.city = city;
		this.stateCode = stateCode;
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

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@Override
	public String toString() {
		return "PublisherDto [publisherId=" + publisherId + ", name=" + name + ", city=" + city + ", stateCode="
				+ stateCode + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, name, publisherId, stateCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PublisherDto other = (PublisherDto) obj;
		return Objects.equals(city, other.city) && Objects.equals(name, other.name)
				&& Objects.equals(publisherId, other.publisherId) && Objects.equals(stateCode, other.stateCode);
	}

}
