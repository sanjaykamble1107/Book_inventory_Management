package com.bookinventorymanagement.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookinventorymanagement.entity.User;
import com.bookinventorymanagement.repository.UserRepository;

@RestController
@RequestMapping("/home")
public class Homecontroller {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/user")
	public List<User> getUser() {
		System.out.println("getting user");
		
		return userRepository.findAll();
		
	}
	


}
