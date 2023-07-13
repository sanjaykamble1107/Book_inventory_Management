package com.bookinventorymanagement.service;

import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.dto.UserDto;

public interface UserService {

	public ResponseDto saveUser(UserDto dto);
	public UserDto getUserById(Integer userId);
	public UserDto getUserByUsername(String username);
	public UserDto updateFirstNameById(Integer userId,UserDto dto);
	public UserDto updateLastNameById(Integer userId,UserDto dto);
	public UserDto updatePhoneNumberById(Integer userId,UserDto dto);

}
