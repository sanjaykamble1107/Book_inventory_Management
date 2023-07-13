package com.bookinventorymanagement.service;

import java.util.List;

import com.bookinventorymanagement.dto.PublisherDto;
import com.bookinventorymanagement.dto.ResponseDto;

public interface PublisherService {
	
	public ResponseDto savePublisher(PublisherDto dto);
	public List<PublisherDto> getAllPublishers();
	
	public PublisherDto getPublisherById(Integer publisherId);
	public PublisherDto getPublisherByName(String name);
	
	public List<PublisherDto> getAllPublisherByCity(String city);

	public PublisherDto updateNameByPublisherId(Integer publisherId, PublisherDto dto);
	public PublisherDto updateCityByPublisherId(Integer publisherId, PublisherDto dto);
	public PublisherDto updateStateCodeByPublisherId(Integer publisherId, PublisherDto dto);
	
	public List<PublisherDto> getAllPublisherByStateCode(String stateCode);
	
	
	
	
}
