package com.bookinventorymanagement.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.entity.User;
import com.bookinventorymanagement.repository.UserRepository;


@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findByUserName(username).orElseThrow(()->new RuntimeException("user not found"));
//		return user;
		
		   Optional<User> user = userRepository.findByUserName(username);
	        if (user.isEmpty())
	            throw new UsernameNotFoundException("Username not found",null);
	        
	        return new org.springframework.security.core.userdetails.User(user.get().getuserName(), user.get().getPassword(),user.get().getAuthorities());
	    }
		
	}


