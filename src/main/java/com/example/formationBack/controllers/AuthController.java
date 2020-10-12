package com.example.formationBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.formationBack.models.AuthenticationRequest;
import com.example.formationBack.models.AuthenticationResponse;
import com.example.formationBack.models.SignUpRequest;
import com.example.formationBack.models.User;
import com.example.formationBack.repositories.UserRepository;
import com.example.formationBack.security.MyUserDetailsService;
import com.example.formationBack.security.utils.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	MyUserDetailsService userDetailsService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtUtils.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt, "Login is successful."));
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signup) throws Exception {
		
		try {
			
			if(userRepository.existsByUsername(signup.getUsername())) {
				return ResponseEntity.badRequest().body("Error: Username already taken!");
			}
			
			if(userRepository.existsByEmail(signup.getEmail())) {
				return ResponseEntity.badRequest().body("Error: Email is already in use!");
			}
			
			User user = new User(signup.getUsername(), encoder.encode(signup.getPassword()), signup.getEmail());
			String role = "USER";
			Boolean isActive = true;
			user.setRoles(role);
			user.setActive(isActive);
			userRepository.save(user);
			
		} catch (BadCredentialsException e) {
			throw new Exception("An error happen during the registred process.", e);
		}
		
		return ResponseEntity.ok("User successfully registred");
	}

}
