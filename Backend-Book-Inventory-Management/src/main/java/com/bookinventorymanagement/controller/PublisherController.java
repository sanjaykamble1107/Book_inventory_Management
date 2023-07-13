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

import com.bookinventorymanagement.dto.PublisherDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.service.PublisherService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/publisher")
@Validated
@CrossOrigin(origins="http://localhost:4200/",exposedHeaders = "token")
public class PublisherController {
	
	@Autowired
	private PublisherService publisherService;
	
	// Save a publisher
	@PostMapping("/post")
	public ResponseEntity<ResponseDto> savePublisher(@Valid @RequestBody PublisherDto dto) {
		ResponseDto savePublisher = publisherService.savePublisher(dto);
		return new ResponseEntity<ResponseDto>(savePublisher, HttpStatus.OK);	
	}
	
	// Get all publishers
	@GetMapping
	public ResponseEntity<List<PublisherDto>> getAllPublishers() {
		List<PublisherDto> publisherDto = publisherService.getAllPublishers();
		return new ResponseEntity<List<PublisherDto>>(publisherDto, HttpStatus.OK);
	}
	
	// Get a publisher by ID
	@GetMapping("/{publisherId}")
	public ResponseEntity<PublisherDto> getPublisherById(@PathVariable Integer publisherId) {
		PublisherDto publisherDto = publisherService.getPublisherById(publisherId);
		return new ResponseEntity<PublisherDto>(publisherDto, HttpStatus.OK);
	}
	
	// Get a publisher by name
	@GetMapping("/name/{name}")
	public ResponseEntity<PublisherDto> getPublisherByName(@PathVariable String name) {
		PublisherDto publisherDto = publisherService.getPublisherByName(name);
		return new ResponseEntity<PublisherDto>(publisherDto, HttpStatus.OK);
	}
	
	// Update the name of a publisher by ID
	@PutMapping("/update/name/{publisherId}")
	public ResponseEntity<PublisherDto> updateNameByPublisherId(@PathVariable Integer publisherId, @RequestBody PublisherDto dto) {
		PublisherDto publisherDto = publisherService.updateNameByPublisherId(publisherId, dto);
		return new ResponseEntity<PublisherDto>(publisherDto, HttpStatus.OK);
	}
	
	// Update the city of a publisher by ID
	@PutMapping("/update/city/{publisherId}")
	public ResponseEntity<PublisherDto> updateCityByPublisherId(@PathVariable Integer publisherId,  @RequestBody PublisherDto dto) {
		PublisherDto publisherDto = publisherService.updateCityByPublisherId(publisherId, dto);
		return new ResponseEntity<PublisherDto>(publisherDto, HttpStatus.OK);
	}
	
	// Update the state code of a publisher by ID
	@PutMapping("/update/state/{publisherId}")
	public ResponseEntity<PublisherDto> updateStateCodeByPublisherId(@PathVariable Integer publisherId, @RequestBody PublisherDto dto) {
		PublisherDto publisherDto = publisherService.updateStateCodeByPublisherId(publisherId, dto);
		return new ResponseEntity<PublisherDto>(publisherDto, HttpStatus.OK);
	}
	
	// Get all publishers by city
	@GetMapping("/city/{city}")
	public ResponseEntity<List<PublisherDto>> getAllPublishersByCity(@PathVariable String city) {
		List<PublisherDto> publisherDto = publisherService.getAllPublisherByCity(city);
		return new ResponseEntity<List<PublisherDto>>(publisherDto, HttpStatus.OK);
	}
	
	// Get all publishers by state code
	@GetMapping("/state/{stateCode}")
	public ResponseEntity<List<PublisherDto>> getAllPublishersByStateCode(@PathVariable String stateCode) {
		List<PublisherDto> publisherDto = publisherService.getAllPublisherByStateCode(stateCode);
		return new ResponseEntity<List<PublisherDto>>(publisherDto, HttpStatus.OK);
	}
}
