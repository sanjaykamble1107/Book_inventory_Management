package com.bookinventorymanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.entity.PermRole;
import com.bookinventorymanagement.entity.User;
import com.bookinventorymanagement.repository.PermRoleRepository;
import com.bookinventorymanagement.repository.UserRepository;

@Service
public class AuthenticateService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PermRoleRepository roleRepository;

	public ResponseDto register(RegisterRequest registerRequest) {

		User user = new User();

		user.setfirstName(registerRequest.getFirstname());
		user.setlastName(registerRequest.getLastname());
		user.setuserName(registerRequest.getUsername());
		user.setphoneNumber(registerRequest.getPhoneNumber());
		user.setpassword(passwordEncoder.encode(registerRequest.getPassword()));

		PermRole role = roleRepository.getById(1);
		roleRepository.save(role);

		user.setroleNumber(role);

		userRepository.save(user);
//		String jwtToken=jwtService.generateToken(user);

//		return new JwtResponse(jwtToken);
//		JwtResponse jwtResponse=new JwtResponse();
//		jwtResponse.setRoleNumber(user.getroleNumber().getRollNumber());
//		jwtResponse.setUsername(user.getfirstName());
//		jwtResponse.setJwtToken("user added successfully");

		return new ResponseDto("user added successfully");
	}

}
