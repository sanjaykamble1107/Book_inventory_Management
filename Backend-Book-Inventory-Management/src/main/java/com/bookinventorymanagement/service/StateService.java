package com.bookinventorymanagement.service;

import java.util.List;

import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.dto.StateDto;


public interface StateService {
	public ResponseDto saveState(StateDto dto);
	public List<StateDto> getAllStates();
	public StateDto getStateById(String stateCode);
	public StateDto updateStateNameById(String stateCode,StateDto dto);

}
