package com.bookinventorymanagement.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Validated
public class StateDto {
	@Column(nullable = false, length = 2)
	@NotBlank(message = "StateCode is required")
	@Size(min = 2, max = 2, message = "StateCode should be exactly 2 characters")
	private String stateCode;
	@Column(length = 50)
	@Size(max = 50, message = "StateName should not exceed 50 characters")
	private String stateName;

	public StateDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StateDto(String stateCode, String stateName) {
		super();
		this.stateCode = stateCode;
		this.stateName = stateName;
	}

	
	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		return "StateDto [stateCode=" + stateCode + ", stateName=" + stateName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(stateCode, stateName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StateDto other = (StateDto) obj;
		return Objects.equals(stateCode, other.stateCode) && Objects.equals(stateName, other.stateName);
	}

}
