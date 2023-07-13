package com.bookinventorymanagement.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.dto.PublisherDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.entity.Publisher;
import com.bookinventorymanagement.entity.State;
import com.bookinventorymanagement.exceptions.PublisherNotFoundException;
import com.bookinventorymanagement.exceptions.StateNotFoundException;
import com.bookinventorymanagement.repository.PublisherRepository;
import com.bookinventorymanagement.repository.StateRepository;
import com.bookinventorymanagement.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private StateRepository stateRepository;

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Save a publisher.
	 *
	 * @param dto The PublisherDto containing the publisher details to be saved.
	 * @return The ResponseDto with a success message.
	 * @throws PublisherNotFoundException if a publisher with the same ID already
	 *                                    exists.
	 * @throws StateNotFoundException     if the state with the given state code is
	 *                                    not found.
	 */
	@Override
	public ResponseDto savePublisher(PublisherDto dto) {
		Optional<Publisher> optional = publisherRepository.findById(dto.getPublisherId());
		if (optional.isPresent()) {
			throw new PublisherNotFoundException("Publisher is already present with ID: " + dto.getPublisherId());
		}
		Publisher publisher = new Publisher();
		State state = stateRepository.getById(dto.getStateCode());

		if (state == null) {
			throw new StateNotFoundException("State not found with code: " + dto.getStateCode());
		}

		publisher.setState(state);
		BeanUtils.copyProperties(dto, publisher);

		publisherRepository.save(publisher);
		responseDto.setResponseMessage("Publisher added successfully");
		dto.setStateCode(publisher.getState().getStateCode());
		return responseDto;
	}

	/**
	 * Get all publishers.
	 *
	 * @return The list of PublisherDto containing all the publisher details.
	 */
	@Override
	public List<PublisherDto> getAllPublishers() {
		List<Publisher> list = publisherRepository.findAll();
		List<PublisherDto> dtos = new ArrayList<>();
		for (Publisher publisher : list) {
			PublisherDto publisherDto = new PublisherDto();
			publisherDto.setStateCode(publisher.getState().getStateCode());
			BeanUtils.copyProperties(publisher, publisherDto);
			dtos.add(publisherDto);
		}
		return dtos;
	}

	/**
	 * Get a publisher by its ID.
	 *
	 * @param publisherId The ID of the publisher.
	 * @return The PublisherDto containing the publisher details.
	 * @throws PublisherNotFoundException if the publisher with the given ID is not
	 *                                    found.
	 */
	@Override
	public PublisherDto getPublisherById(Integer publisherId) {
		Optional<Publisher> optional = publisherRepository.findById(publisherId);
		if (optional.isPresent()) {
			PublisherDto publisherDto = new PublisherDto();
			BeanUtils.copyProperties(optional.get(), publisherDto);
			publisherDto.setStateCode(optional.get().getState().getStateCode());
			return publisherDto;
		}
		throw new PublisherNotFoundException("Publisher not found with ID: " + publisherId);
	}

	/**
	 * Get a publisher by its name.
	 *
	 * @param name The name of the publisher.
	 * @return The PublisherDto containing the publisher details.
	 * @throws PublisherNotFoundException if the publisher with the given name is
	 *                                    not found.
	 */
	@Override
	public PublisherDto getPublisherByName(String name) {
		Optional<Publisher> optional = publisherRepository.findByName(name);
		if (optional.isPresent()) {
			PublisherDto publisherDto = new PublisherDto();
			BeanUtils.copyProperties(optional.get(), publisherDto);
			publisherDto.setStateCode(optional.get().getState().getStateCode());
			return publisherDto;
		}
		throw new PublisherNotFoundException("Publisher not found with name: " + name);
	}

	/**
	 * Get all publishers by city.
	 *
	 * @param city The city of the publishers.
	 * @return The list of PublisherDto containing the publishers in the given city.
	 * @throws PublisherNotFoundException if no publisher is found in the given
	 *                                    city.
	 */
	@Override
	public List<PublisherDto> getAllPublisherByCity(String city) {
		Optional<List<Publisher>> optional = publisherRepository.findByCity(city);
		if (optional.isPresent() && !optional.get().isEmpty()) {
			List<PublisherDto> dtos = new ArrayList<>();
			for (Publisher publisher : optional.get()) {
				PublisherDto publisherDto = new PublisherDto();
				BeanUtils.copyProperties(publisher, publisherDto);
				publisherDto.setStateCode(publisher.getState().getStateCode());
				dtos.add(publisherDto);
			}
			return dtos;
		}
		throw new PublisherNotFoundException("Publisher not found in city: " + city);
	}

	/**
	 * Update the name of a publisher by its ID.
	 *
	 * @param publisherId The ID of the publisher.
	 * @param dto         The PublisherDto containing the updated publisher details.
	 * @return The PublisherDto containing the updated publisher details.
	 * @throws PublisherNotFoundException if the publisher with the given ID is not
	 *                                    found.
	 */
	@Override
	public PublisherDto updateNameByPublisherId(Integer publisherId, PublisherDto dto) {
		Optional<Publisher> optionPublisher = publisherRepository.findById(publisherId);
		if (optionPublisher.isPresent()) {
			Publisher publisher = optionPublisher.get();
			publisher.setName(dto.getName());
			publisherRepository.save(publisher);
			PublisherDto publisherDto = new PublisherDto();
			publisherDto.setStateCode(publisher.getState().getStateCode());
			BeanUtils.copyProperties(publisher, publisherDto);
			return publisherDto;
		}
		throw new PublisherNotFoundException("Publisher not found with ID: " + publisherId);
	}

	/**
	 * Update the city of a publisher by its ID.
	 *
	 * @param publisherId The ID of the publisher.
	 * @param dto         The PublisherDto containing the updated publisher details.
	 * @return The PublisherDto containing the updated publisher details.
	 * @throws PublisherNotFoundException if the publisher with the given ID is not
	 *                                    found.
	 */
	@Override
	public PublisherDto updateCityByPublisherId(Integer publisherId, PublisherDto dto) {
		Optional<Publisher> optionPublisher = publisherRepository.findById(publisherId);
		if (optionPublisher.isPresent()) {
			Publisher publisher = optionPublisher.get();
			publisher.setCity(dto.getCity());
			publisherRepository.save(publisher);
			PublisherDto publisherDto = new PublisherDto();
			publisherDto.setStateCode(publisher.getState().getStateCode());
			BeanUtils.copyProperties(publisher, publisherDto);
			return publisherDto;
		}
		throw new PublisherNotFoundException("Publisher not found with ID: " + publisherId);
	}

	/**
	 * Update the state code of a publisher by its ID.
	 *
	 * @param publisherId The ID of the publisher.
	 * @param dto         The PublisherDto containing the updated publisher details.
	 * @return The PublisherDto containing the updated publisher details.
	 * @throws PublisherNotFoundException if the publisher with the given ID is not
	 *                                    found.
	 * @throws StateNotFoundException     if the state with the given state code is
	 *                                    not found.
	 */
	@Override
	public PublisherDto updateStateCodeByPublisherId(Integer publisherId, PublisherDto dto) {
		Optional<Publisher> optionalPublisher = publisherRepository.findById(publisherId);
		if (optionalPublisher.isPresent()) {
			Publisher publisher = optionalPublisher.get();
			Optional<State> state = stateRepository.findById(dto.getStateCode());
			if (state.isEmpty()) {
				throw new StateNotFoundException("State not found with code: " + dto.getStateCode());
			}
			publisher.setState(stateRepository.getById(dto.getStateCode()));
			publisherRepository.save(publisher);
			PublisherDto dto2 = new PublisherDto();
			dto2.setStateCode(publisher.getState().getStateCode());
			BeanUtils.copyProperties(publisher, dto2);
			return dto2;
		}
		throw new PublisherNotFoundException("Publisher not found with ID: " + publisherId);
	}

	/**
	 * Get all publishers by state code.
	 *
	 * @param stateCode The state code of the publishers.
	 * @return The list of PublisherDto containing the publishers in the given state
	 *         code.
	 * @throws PublisherNotFoundException if no publisher is found in the given
	 *                                    state code.
	 */
	@Override
	public List<PublisherDto> getAllPublisherByStateCode(String stateCode) {
		List<Publisher> publishers = publisherRepository.findAll();
		List<Publisher> state = publishers.stream().filter(p -> p.getState().getStateCode().equals(stateCode))
				.collect(Collectors.toList());
		if (!state.isEmpty()) {
			List<PublisherDto> dtos = new ArrayList<>();
			for (Publisher p : state) {
				PublisherDto dto = new PublisherDto();
				dto.setStateCode(p.getState().getStateCode());
				BeanUtils.copyProperties(p, dto);
				dtos.add(dto);
			}
			return dtos;
		}
		throw new PublisherNotFoundException("Publisher not found in state with code: " + stateCode);
	}

}
