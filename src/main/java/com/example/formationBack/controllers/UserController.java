package com.example.formationBack.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.formationBack.models.Profile;
import com.example.formationBack.models.User;
import com.example.formationBack.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	// récupère profil de l'utilisateur
	@GetMapping("/profile")
	public Profile getProfile(@RequestParam("username") String username) throws Exception {
		
		try {
			Optional<User> user = userRepository.findByUsername(username);
			Profile profile = new Profile(user.get().getUsername(), user.get().getEmail());
			return profile;
			
		} catch (BadCredentialsException e) {
			throw new Exception("This user profile does not exist", e);
		}
	}

}
