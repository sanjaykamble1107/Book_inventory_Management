package com.bookinventorymanagement.service;

import com.bookinventorymanagement.dto.BookConditionDto;
import com.bookinventorymanagement.dto.ResponseDto;

public interface BookConditionService {
	
	public ResponseDto saveBookCondition(BookConditionDto conditionDto);
	public BookConditionDto getBookConditionById(Integer ranks);
	public BookConditionDto updateDescriptionById(Integer ranks,BookConditionDto conditionDto);
	public BookConditionDto updateFullDescriptionById(Integer ranks,BookConditionDto conditionDto);
	public BookConditionDto updatePriceById(Integer ranks,BookConditionDto bookConditionDto);

}
