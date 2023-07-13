package com.bookinventorymanagement.controller;

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

import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.dto.UserDto;
import com.bookinventorymanagement.service.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Save a user
	@PostMapping("/post")
	public ResponseEntity<ResponseDto> saveUser(@Valid @RequestBody UserDto dto) {
		ResponseDto saveUser = userService.saveUser(dto);
		return new ResponseEntity<ResponseDto>(saveUser, HttpStatus.OK);	
	}
	
	// Get a user by username
	@GetMapping("username/{username}")
	public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
		UserDto userDto = userService.getUserByUsername(username);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	// Get a user by ID
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		UserDto userDto = userService.getUserById(userId);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	// Update the first name of a user by ID
	@PutMapping("/update/firstname/{userId}")
	public ResponseEntity<UserDto> updateFirstNameById(@PathVariable Integer userId, @RequestBody UserDto dto) {
		UserDto userDto = userService.updateFirstNameById(userId, dto);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	// Update the last name of a user by ID
	@PutMapping("/update/lastname/{userId}")
	public ResponseEntity<UserDto> updateLastNameById(@PathVariable Integer userId, @RequestBody UserDto dto) {
		UserDto userDto=userService.updateLastNameById(userId, dto);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	// Update the phone number of a user by ID
	@PutMapping("/update/phonenumber/{userId}")
	public ResponseEntity<UserDto> updatePhoneNumberById(@PathVariable Integer userId, @RequestBody UserDto dto) {
		UserDto userDto=userService.updatePhoneNumberById(userId, dto);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
}
