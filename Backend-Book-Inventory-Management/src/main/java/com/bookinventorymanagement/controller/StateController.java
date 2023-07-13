package com.bookinventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.dto.StateDto;
import com.bookinventorymanagement.service.StateService;

import jakarta.validation.Valid;

@CrossOrigin(origins="http://localhost:4200/")

@RestController
@RequestMapping("/state")
@Validated
public class StateController {
	@Autowired
	private StateService stateService;
	
	// Save a state
	@PostMapping("/add")
	public ResponseEntity<ResponseDto> saveState(@Valid @RequestBody StateDto dto) {
		ResponseDto saveState = stateService.saveState(dto);
		return new ResponseEntity<ResponseDto>(saveState, HttpStatus.OK);	
	}
	
	// Get a state by state code
	@GetMapping("/{stateCode}")
	public ResponseEntity<StateDto> getStateById(@PathVariable String stateCode) {
		StateDto stateDto = stateService.getStateById(stateCode);
		return new ResponseEntity<StateDto>(stateDto, HttpStatus.OK);
	}
	
	// Get all states
	@GetMapping
	public ResponseEntity<List<StateDto>> getAllStates() {
		List<StateDto> stateDto = stateService.getAllStates();
		return new ResponseEntity<List<StateDto>>(stateDto, HttpStatus.OK);
	}
	
	// Update the name of a state by state code
	@PutMapping("/update/name/{stateCode}")
	public ResponseEntity<StateDto> updateStateNameById(@PathVariable String stateCode, @RequestBody StateDto dto) {
		StateDto stateDto=stateService.updateStateNameById(stateCode, dto);
		return new ResponseEntity<StateDto>(stateDto, HttpStatus.OK);
	}
}
