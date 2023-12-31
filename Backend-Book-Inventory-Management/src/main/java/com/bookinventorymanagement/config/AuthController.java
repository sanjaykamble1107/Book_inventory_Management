
package com.bookinventorymanagement.config;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookinventorymanagement.entity.User;
import com.bookinventorymanagement.repository.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {

	@Autowired
	private AuthenticateService authenticateService;
	@Autowired
	private CustomUserDetailService userDetailService;
	

//	@Autowired
//	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserRepository userRepository;
	
	

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

		this.doAuthenticate(request.getUserName(),request.getPassword());

		UserDetails userDetails = userDetailService.loadUserByUsername(request.getUserName());
		String token = this.jwtService.generateToken(userDetails);

		JwtResponse response = new JwtResponse();
		response.setJwtToken(token);
		
		Optional<User> user=userRepository.findByUserName(userDetails.getUsername());
		
		
		response.setUsername(userDetails.getUsername());
		
		
		response.setRoleNumber(user.get().getroleNumber().getPermRole());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
		
		if (userRepository.findByUserName(registerRequest.getUsername()).isPresent())
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);

		return ResponseEntity.ok(authenticateService.register(registerRequest));

	}

	
	

	private void doAuthenticate(String userName, String password) {

		Optional<User> optional = userRepository.findByUserName(userName);
		
		if(optional.isEmpty()) 
		{
			System.out.println("*****####");
		}
		UsernamePasswordAuthenticationToken authentication;
		try {
			authentication = new UsernamePasswordAuthenticationToken(userName, password,optional.get().getAuthorities());
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Invalid Username or password !!");
		}

		try {
			authenticationManager.authenticate(authentication);

		} catch (BadCredentialsException ex) {
			throw new BadCredentialsException("Invalid Username or password !!");
		}
	}

	
}
