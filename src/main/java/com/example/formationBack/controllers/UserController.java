package com.example.formationBack.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping("/profile/{username}")
	public Profile getProfile(@PathVariable("username") String username, Principal principal) throws Exception {
		
		if(username.equals(principal.getName())) {
			try {
				User user = userRepository.findByUsername(username);
				Profile profile = new Profile(user.getUsername(), user.getEmail());
				return profile;
				
			} catch (BadCredentialsException e) {
				throw new Exception("This user profile does not exist", e);
			}
		} else {
			throw new Exception("Error. You do not have access to these datas or problem happen when trying to retrieve your data");
		}
	
		
	}
	
	//modifie infos du profil
	@PutMapping("/profile/{username}")
	public String updateProfile(@PathVariable("username") String username, Principal principal, @RequestBody Profile profile) throws Exception {
		if(username.equals(principal.getName())) {
			try {
				User user = userRepository.findByUsername(username);
				 user.setEmail(profile.getEmail());
				userRepository.save(user);
				return "user updated";
			} catch (BadCredentialsException e) {
				throw new Exception("Error when updating this profile. Please try again", e);
			}
		} else {
			throw new Exception("Error.You can only update your profile");
		}
			
	}
	
	//upload picture on user profile
	

}
