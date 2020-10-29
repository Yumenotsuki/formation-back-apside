package com.example.formationBack.controllers;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.formationBack.models.AuthenticationRequest;
import com.example.formationBack.models.AuthenticationResponse;
import com.example.formationBack.models.ConfirmationToken;
import com.example.formationBack.models.PasswordResetToken;
import com.example.formationBack.models.SignUpRequest;
import com.example.formationBack.models.UpdatePassword;
import com.example.formationBack.models.User;
import com.example.formationBack.repositories.ConfirmationTokenRepository;
import com.example.formationBack.repositories.PasswordResetTokenRepository;
import com.example.formationBack.repositories.UserRepository;
import com.example.formationBack.security.MyUserDetailsService;
import com.example.formationBack.security.utils.JwtUtils;
import com.example.formationBack.services.EmailService;

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
	
	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	PasswordResetTokenRepository resetTokenRepository;
	
	@Autowired
	EmailService emailService;
	
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
			//Boolean isActive = true;
			user.setRoles(role);
			//user.setActive(isActive);
			userRepository.save(user);
			
			ConfirmationToken confirmationToken = new ConfirmationToken(user);
			confirmationTokenRepository.save(confirmationToken);
			
			//envoie email pour valider le compte utilisateur qui vient d'être crée
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("Complete Registration");
			mailMessage.setFrom("testdevelop.lab@gmail.com");
			mailMessage.setText("To confirm your account, please click here: "+"http://localhost:8080/auth/confirm-account?token="+confirmationToken.getConfirmationToken());
			
			emailService.sendEmail(mailMessage);
			
		} catch (BadCredentialsException e) {
			throw new Exception("An error happen during the registred process.", e);
		}
		
		return ResponseEntity.ok("User successfully registred");
	}
	
	@RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) throws Exception {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		try {
			if(token != null) {
				User user = userRepository.findByEmail(token.getUser().getEmail());
				user.setActive(true);
				userRepository.save(user);
			} else {
				throw new Exception("The link is invalid or broken!");
			}
		} catch (BadCredentialsException e) {
			throw new Exception("An error appears during the validation process", e);
		}
		return ResponseEntity.ok("Your account has been verified");		
		
	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public ResponseEntity<?> updatePassword(@RequestBody UpdatePassword password) throws Exception {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		String existingPassword = passwordEncoder.encode(password.getPassword()) ; //password entered by user
		String dbPassword = user.getPassword();  //load hashed DB password
		
		if(existingPassword != dbPassword) {
			//met à jour mot de passe
			user.setPassword(existingPassword);
			userRepository.save(user);
		} else {
			throw new Exception("An error appears during the password update process");
		}
		
		return ResponseEntity.ok("Your password has been successfully updated.");	
	}
	
	@RequestMapping(value = "/sendForgotPasswordEmail", method = RequestMethod.POST)
	public ResponseEntity<?> sendForgotPasswordEmail(@RequestBody SignUpRequest request) throws Exception {
		System.out.println(request.getEmail());
		try {
			//search user in database
			User user = userRepository.findByEmail(request.getEmail());
			
			//check if user is not null
			if(user == null) {
				throw new UsernameNotFoundException("User not found");
			}
			//create reset token
			PasswordResetToken reset = new PasswordResetToken(user);
			//save reset token in database
			resetTokenRepository.save(reset);
			
			//send email
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Reset your password");
			passwordResetEmail.setFrom("testdevelop.lab@gmail.com");
			passwordResetEmail.setText("To reset your password, please click here: "+"http://localhost:8080/auth/reset?token="+ reset.getResetToken());
			
			emailService.sendEmail(passwordResetEmail);
			
		} catch (BadCredentialsException e) {
			throw new Exception("An error appears during the password reset process");
		}
		
		return ResponseEntity.ok("A password reset link has been sent to you.");
	}
	//the user can't choose his new password when clicking on the link
	@RequestMapping(value = "/reset", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> setNewPassword(@RequestParam("token") String resetToken) throws Exception {
		PasswordResetToken reset = resetTokenRepository.findByResetToken(resetToken);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		try {
			//check if reset token is present in db
			if(reset != null) {
				//search user by is email present in the reset token
				User user = userRepository.findByEmail(reset.getUser().getEmail());
				//set a new encode password with hello
				user.setPassword(passwordEncoder.encode("hello"));
				userRepository.save(user);
				//set the reset token to null so it cannot be used again
				reset.setResetToken(null);
				resetTokenRepository.save(reset);
			} else {
				throw new Exception("The link is invalid or broken!");
			}
		} catch (BadCredentialsException e) {
			throw new Exception("An error appears during the password reset process", e);
		}
		return ResponseEntity.ok("Your new password has been set.");		
	}
	
	

}
