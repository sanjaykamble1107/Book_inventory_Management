package com.bookinventorymanagement.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.dto.StateDto;
import com.bookinventorymanagement.entity.State;
import com.bookinventorymanagement.exceptions.StateNotFoundException;
import com.bookinventorymanagement.repository.StateRepository;
import com.bookinventorymanagement.service.StateService;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateRepository stateRepository;

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Save a state.
	 *
	 * @param dto The StateDto containing the state details to be saved.
	 * @return The ResponseDto with a success message.
	 * @throws StateNotFoundException if a state with the same state code already
	 *                                exists.
	 */
	@Override
	public ResponseDto saveState(StateDto dto) {
		Optional<State> optional = stateRepository.findById(dto.getStateCode());
		if (optional.isPresent()) {
			throw new StateNotFoundException("State is already present with state code: " + dto.getStateCode());
		}

		State state = new State();
		BeanUtils.copyProperties(dto, state);
		responseDto.setResponseMessage("State added successfully");
		stateRepository.save(state);
		return responseDto;
	}

	/**
	 * Get all states.
	 *
	 * @return The list of StateDto objects containing all the states.
	 */
	@Override
	public List<StateDto> getAllStates() {
		List<State> list = stateRepository.findAll();
		List<StateDto> dtos = new ArrayList<>();
		for (State state : list) {
			StateDto stateDto = new StateDto();
			BeanUtils.copyProperties(state, stateDto);
			dtos.add(stateDto);
		}
		return dtos;
	}

	/**
	 * Get a state by state code.
	 *
	 * @param stateCode The state code of the state.
	 * @return The StateDto containing the state details.
	 * @throws StateNotFoundException if the state with the given state code is not
	 *                                found.
	 */
	@Override
	public StateDto getStateById(String stateCode) {
		Optional<State> optional = stateRepository.findById(stateCode);
		if (optional.isPresent()) {
			StateDto stateDto = new StateDto();
			BeanUtils.copyProperties(optional.get(), stateDto);
			return stateDto;
		}
		throw new StateNotFoundException("State not found with state code: " + stateCode);
	}

	/**
	 * Update the state name by state code.
	 *
	 * @param stateCode The state code of the state to be updated.
	 * @param dto       The StateDto containing the updated state details.
	 * @return The StateDto containing the updated state details.
	 * @throws StateNotFoundException if the state with the given state code is not
	 *                                found.
	 */
	@Override
	public StateDto updateStateNameById(String stateCode, StateDto dto) {
		Optional<State> optionalState = stateRepository.findById(stateCode);
		if (optionalState.isPresent()) {
			State state = optionalState.get();
			state.setStateName(dto.getStateName());
			stateRepository.save(state);
			StateDto stateDto = getStateById(stateCode);
			BeanUtils.copyProperties(state,stateDto);
			return stateDto;
		}
		throw new StateNotFoundException("State not found with state code: " + stateCode);
	}

}
