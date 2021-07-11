package com.exam.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtUtil;
import com.exam.entities.JwtRequest;
import com.exam.entities.JwtResponse;
import com.exam.entities.User;
import com.exam.services.impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@PostMapping("/generateToken")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
			
		}
		catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User Not found");
		}
		
		///authenticate user
		UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtil.generateToken(userDetails);
		System.out.println("Token generate :"+token);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username,String password) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch (DisabledException e) {
			// TODO: handle exception
			throw new Exception("User Disabled"+ e.getMessage());
		}
		catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception("Bad Credentials "+e.getMessage());
		}
		
	}
	
	@RequestMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return (User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
	}
}
