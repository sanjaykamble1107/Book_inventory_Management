package com.bookinventorymanagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.dto.ReviewerDto;
import com.bookinventorymanagement.entity.Reviewer;
import com.bookinventorymanagement.exceptions.ReviewerNotFoundException;
import com.bookinventorymanagement.repository.ReviewerRepository;
import com.bookinventorymanagement.service.ReviewerService;

@Service
public class ReviewerServiceImpl implements ReviewerService {

	@Autowired
	private ReviewerRepository repository;

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Save a reviewer.
	 *
	 * @param reviewerDto The ReviewerDto containing the reviewer details to be
	 *                    saved.
	 * @return The ResponseDto with a success message.
	 * @throws ReviewerNotFoundException if a reviewer with the same reviewer ID
	 *                                   already exists.
	 */
	@Override
	public ResponseDto saveReviewer(ReviewerDto reviewerDto) {
		Optional<Reviewer> optional = repository.findById(reviewerDto.getReviewerId());
		if (optional.isPresent()) {
			throw new ReviewerNotFoundException(
					"Reviewer is already present with Reviewer ID: " + reviewerDto.getReviewerId());
		}

		Reviewer reviewer = new Reviewer();
		BeanUtils.copyProperties(reviewerDto, reviewer);
		responseDto.setResponseMessage("Reviewer added successfully");
		repository.save(reviewer);
		return responseDto;
	}

	/**
	 * Get a reviewer by ID.
	 *
	 * @param id The ID of the reviewer.
	 * @return The ReviewerDto containing the reviewer details.
	 * @throws ReviewerNotFoundException if the reviewer with the given ID is not
	 *                                   found.
	 */
	@Override
	public ReviewerDto getReviewerById(Integer id) {
		Optional<Reviewer> findById = repository.findById(id);
		if (findById.isPresent()) {
			ReviewerDto reviewerDto = new ReviewerDto();
			BeanUtils.copyProperties(findById.get(), reviewerDto);
			return reviewerDto;
		}
		throw new ReviewerNotFoundException("Reviewer not found with ID: " + id);
	}

	/**
	 * Update a reviewer by ID.
	 *
	 * @param id  The ID of the reviewer.
	 * @param dto The ReviewerDto containing the updated reviewer details.
	 * @return The ReviewerDto containing the updated reviewer details.
	 * @throws ReviewerNotFoundException if the reviewer with the given ID is not
	 *                                   found.
	 */
	@Override
	public ReviewerDto updateReviewerById(Integer id, ReviewerDto dto) {
		Optional<Reviewer> optionalReviewer = repository.findById(id);

		if (optionalReviewer.isPresent()) {
			Reviewer reviewer = optionalReviewer.get();
			reviewer.setname(dto.getName());
			repository.save(reviewer);
			ReviewerDto reviewerDto = new ReviewerDto();

			BeanUtils.copyProperties(reviewer, reviewerDto);
			return reviewerDto;
		}
		throw new ReviewerNotFoundException("Reviewer not found with ID: " + id);
	}

	/**
	 * Update the employer of a reviewer by ID.
	 *
	 * @param id  The ID of the reviewer.
	 * @param dto The ReviewerDto containing the updated reviewer details.
	 * @return The ReviewerDto containing the updated reviewer details.
	 * @throws ReviewerNotFoundException if the reviewer with the given ID is not
	 *                                   found.
	 */
	@Override
	public ReviewerDto updateEmployeeById(Integer id, ReviewerDto dto) {
		Optional<Reviewer> optionalReviewer = repository.findById(id);

		if (optionalReviewer.isPresent()) {
			Reviewer reviewer = optionalReviewer.get();
			reviewer.setemployedBy(dto.getEmployedBy());
			repository.save(reviewer);
			ReviewerDto reviewerDto = new ReviewerDto();

			BeanUtils.copyProperties(reviewer, reviewerDto);
			return reviewerDto;
		}
		throw new ReviewerNotFoundException("Reviewer not found with ID: " + id);
	}
}
