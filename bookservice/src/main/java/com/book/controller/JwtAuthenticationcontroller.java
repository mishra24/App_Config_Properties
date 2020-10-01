package com.book.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.book.dto.JwtAuthRequest;
import com.book.dto.JwtAuthResponse;
import com.book.exceptions.UserDetailsException;
import com.book.service.impl.MyUserDetailsService;
import com.book.util.APIResponse;
import com.book.util.JwtUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping(value = "/authenticate")
public class JwtAuthenticationcontroller {

	private static final Logger LOGGER = LogManager.getLogger(JwtAuthenticationcontroller.class);
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@ApiOperation(value = "Request for JWT Token", response = String.class)
	@ApiResponses(value = { @ApiResponse(response = APIResponse.class, code = 200, message = "Generate token") })
	@PostMapping()
	public ResponseEntity<Object> createAuthenticationToken(@Valid @RequestBody JwtAuthRequest jwtAuthRequest) {
		LOGGER.debug("Generating the token from username and password");
		authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());

		final String token = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtAuthResponse(token));

	}

	private void authenticate(String userName, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (BadCredentialsException e) {
			LOGGER.error("credentials exceptions", e);
			throw new UserDetailsException("Credentials are wrong");
		}
	}
}
