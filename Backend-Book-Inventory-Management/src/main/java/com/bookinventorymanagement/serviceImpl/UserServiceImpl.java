package com.bookinventorymanagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.dto.UserDto;
import com.bookinventorymanagement.entity.User;
import com.bookinventorymanagement.exceptions.UserNotFoundException;
import com.bookinventorymanagement.repository.PermRoleRepository;
import com.bookinventorymanagement.repository.UserRepository;
import com.bookinventorymanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PermRoleRepository permRoleRepository;

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Save a user.
	 *
	 * @param dto The UserDto containing the user details to be saved.
	 * @return The ResponseDto with a success message.
	 * @throws UserNotFoundException if a user with the same user id already exists.
	 */
	@Override
	public ResponseDto saveUser(UserDto dto) {
		Optional<User> optional = userRepository.findById(dto.getUserId());
		if(optional.isPresent()) {
			throw new UserNotFoundException("User is already present with id: " + dto.getUserId());
		}
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		user.setroleNumber(permRoleRepository.getById(dto.getRoleNumber()));
		responseDto.setResponseMessage("User added successfully");
		userRepository.save(user);
		return responseDto;
	}

	/**
	 * Get a user by user id.
	 *
	 * @param userId The user id of the user.
	 * @return The UserDto containing the user details.
	 * @throws UserNotFoundException if the user with the given user id is not found.
	 */
	@Override
	public UserDto getUserById(Integer userId) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(optional.get(), userDto);
			userDto.setRoleNumber(optional.get().getroleNumber().getRollNumber());
			return userDto;
		}
		throw new UserNotFoundException("User not found with id: " + userId);
	}

	/**
	 * Update the first name of a user by user id.
	 *
	 * @param userId The user id of the user to be updated.
	 * @param dto    The UserDto containing the updated user details.
	 * @return The UserDto containing the updated user details.
	 * @throws UserNotFoundException if the user with the given user id is not found.
	 */
	@Override
	public UserDto updateFirstNameById(Integer userId, UserDto dto) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setfirstName(dto.getFirstName());
			userRepository.save(user);
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			userDto.setRoleNumber(optionalUser.get().getroleNumber().getRollNumber());
			return userDto;
		}
		throw new UserNotFoundException("User not found with id: " + userId);
	}

	/**
	 * Update the last name of a user by user id.
	 *
	 * @param userId The user id of the user to be updated.
	 * @param dto    The UserDto containing the updated user details.
	 * @return The UserDto containing the updated user details.
	 * @throws UserNotFoundException if the user with the given user id is not found.
	 */
	@Override
	public UserDto updateLastNameById(Integer userId, UserDto dto) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setlastName(dto.getLastName());
			userRepository.save(user);
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			userDto.setRoleNumber(optionalUser.get().getroleNumber().getRollNumber());
			return userDto;
		}
		throw new UserNotFoundException("User not found with id: " + userId);
	}

	/**
	 * Update the phone number of a user by user id.
	 *
	 * @param userId The user id of the user to be updated.
	 * @param dto    The UserDto containing the updated user details.
	 * @return The UserDto containing the updated user details.
	 * @throws UserNotFoundException if the user with the given user id is not found.
	 */
	@Override
	public UserDto updatePhoneNumberById(Integer userId, UserDto dto) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setphoneNumber(dto.getPhoneNumber());
			userRepository.save(user);
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			userDto.setRoleNumber(optionalUser.get().getroleNumber().getRollNumber());
			return userDto;
		}
		throw new UserNotFoundException("User not found with id: " + userId);
	}

	/**
	 * Get a user by username.
	 *
	 * @param username The username of the user.
	 * @return The UserDto containing the user details.
	 */
	@Override
	public UserDto getUserByUsername(String username) {
		Optional<User> optional = userRepository.findByUserName(username);
		if(optional.isPresent()) {
			UserDto dto = new UserDto();
			BeanUtils.copyProperties(optional.get(), dto);
			dto.setRoleNumber(optional.get().getroleNumber().getRollNumber());
			return dto;
		}
		return null;
	}

}

