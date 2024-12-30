package com.online.controllers;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.online.dto.AuthenticationRequest;
import com.online.entities.User;
import com.online.repositories.UserRepository;
import com.online.services.jwt.UserDetailsServiceImpl;
import com.online.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	
	@PostMapping("/authenticate")
	public void createAuthentication(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response) throws IOException, JSONException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e) {
			throw new BadCredentialsException("Invalid username or Password");
		}
		catch(DisabledException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,"User does not exist");
		}
		
		final UserDetails userDetails=  userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		
		if((optionalUser.isPresent())) {
			response.getWriter().write(new JSONObject().put("userId", optionalUser.get().getId()).put("role", optionalUser.get().getRole()).toString());
		}
		
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.setHeader("Access-Control-Allow-Headers", "Authorization,X-Pingother,Origin,X-Requested-with,Content-Type,Accept,X-Custom-header");
		response.setHeader("Access-Control-Allow-Origin", "*");  // Or the specific frontend URL
		response.setHeader(HEADER_STRING,TOKEN_PREFIX+jwt);
		System.out.println(jwt);
		return ;	
	}
}
