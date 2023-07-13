package com.bookinventorymanagement.service;

import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.dto.ReviewerDto;


public interface ReviewerService {
	
	public  ResponseDto saveReviewer(ReviewerDto reviewerDto);
	public  ReviewerDto getReviewerById(Integer id);
	public ReviewerDto updateReviewerById(Integer id,ReviewerDto reviewerDto);
	public ReviewerDto updateEmployeeById(Integer id, ReviewerDto reviewerDto );
	

}
