package com.bookinventorymanagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.dto.BookConditionDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.entity.BookCondition;
import com.bookinventorymanagement.exceptions.BookConditionNotFoundException;
import com.bookinventorymanagement.repository.BookConditionRepository;
import com.bookinventorymanagement.service.BookConditionService;
@Service
public class BookConditionServiceImpl implements BookConditionService {

	@Autowired
	private BookConditionRepository conditionRepository;

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Save a book condition to the repository.
	 * 
	 * @param conditionDto The BookConditionDto containing the book condition
	 *                     details.
	 * @return The ResponseDto indicating the success status of the operation.
	 * @throws BookConditionNotFoundException if the book condition with the same
	 *                                        rank already exists.
	 */
	@Override
	public ResponseDto saveBookCondition(BookConditionDto conditionDto) {
		Optional<BookCondition> optional = conditionRepository.findById(conditionDto.getRanks());
		if (optional.isPresent()) {
			throw new BookConditionNotFoundException(
					"Book condition is already present with Rank: " + conditionDto.getRanks());
		}
		BookCondition bookCondition = new BookCondition();
		BeanUtils.copyProperties(conditionDto, bookCondition);
		responseDto.setResponseMessage("Book condition added successfully");
		conditionRepository.save(bookCondition);
		return responseDto;
	}

	/**
	 * Get a book condition by rank.
	 * 
	 * @param ranks The rank of the book condition.
	 * @return The BookConditionDto containing the book condition details.
	 * @throws BookConditionNotFoundException if the book condition with the given
	 *                                        rank is not found.
	 */
	@Override
	public BookConditionDto getBookConditionById(Integer ranks) {
		Optional<BookCondition> findById = conditionRepository.findById(ranks);
		if (findById.isPresent()) {
			BookConditionDto conditionDto = new BookConditionDto();
			BeanUtils.copyProperties(findById.get(), conditionDto);
			return conditionDto;
		}
		throw new BookConditionNotFoundException("Book condition not found with ranks: " + ranks);
	}

	/**
	 * Update the description of a book condition by rank.
	 * 
	 * @param ranks        The rank of the book condition to update.
	 * @param conditionDto The BookConditionDto containing the updated description.
	 * @return The updated BookConditionDto.
	 * @throws BookConditionNotFoundException if the book condition with the given
	 *                                        rank is not found.
	 */
	@Override
	public BookConditionDto updateDescriptionById(Integer ranks, BookConditionDto bookconditionDto) {
		Optional<BookCondition> findById = conditionRepository.findById(ranks);
		if (findById.isPresent()) {
			BookCondition bookCondition =findById.get();
            bookCondition.setDescription(bookconditionDto.getDescription());
            conditionRepository.save(bookCondition);	            
            BookConditionDto bookConditionDtos = new BookConditionDto();	            
            BeanUtils.copyProperties(bookCondition,bookConditionDtos);
            return bookConditionDtos;
		}
		throw new BookConditionNotFoundException("Book condition not found with ranks: " + ranks);

	}

	/**
	 * Update the full description of a book condition by rank.
	 * 
	 * @param ranks        The rank of the book condition to update.
	 * @param conditionDto The BookConditionDto containing the updated full
	 *                     description.
	 * @return The updated BookConditionDto.
	 * @throws BookConditionNotFoundException if the book condition with the given
	 *                                        rank is not found.
	 */
	

	@Override
	public BookConditionDto updateFullDescriptionById(Integer ranks, BookConditionDto bookconditionDto) {
		Optional<BookCondition> findById = conditionRepository.findById(ranks);
		if (findById.isPresent()) {
			BookCondition bookCondition =findById.get();
            bookCondition.setFullDescription(bookconditionDto.getFullDescription());
            conditionRepository.save(bookCondition);	            
            BookConditionDto bookConditionDtos = new BookConditionDto();	            
            BeanUtils.copyProperties(bookCondition,bookConditionDtos);
            return bookConditionDtos;
		}
		throw new BookConditionNotFoundException("Book condition not found with ranks: " + ranks);

	}
	/**
	 * Update the price of a book condition by rank.
	 * 
	 * @param ranks            The rank of the book condition to update.
	 * @param bookConditionDto The BookConditionDto containing the updated price.
	 * @return The updated BookConditionDto.
	 * @throws BookConditionNotFoundException if the book condition with the given
	 *                                        rank is not found.
	 */
	@Override
	public BookConditionDto updatePriceById(Integer ranks, BookConditionDto bookConditionDto) {
		Optional<BookCondition> findById = conditionRepository.findById(ranks);
		if (findById.isPresent()) {
			BookCondition bookCondition =findById.get();
            bookCondition.setPrice(bookConditionDto.getPrice());
            conditionRepository.save(bookCondition);	            
            BookConditionDto bookConditionDtos = new BookConditionDto();	            
            BeanUtils.copyProperties(bookCondition,bookConditionDtos);
            return bookConditionDtos;
		}
		throw new BookConditionNotFoundException("Book condition not found with ranks: " + ranks);

	}

}


