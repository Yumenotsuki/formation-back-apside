package com.example.formationBack.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {
	
	@GetMapping("/home")
	public String home() {
		return "Welcome";
	}
	
	@GetMapping("/user")
	public String user() {
		return "Welcome user";
	}

}
