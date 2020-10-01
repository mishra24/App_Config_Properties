package com.book.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.book.util.JwtUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthenticationcontrollerTest {

	@InjectMocks
	JwtAuthenticationcontroller jwtAuthenticationcontroller;

	@Mock
	JwtUtil jwtUtil;

	@Autowired
	private MockMvc mvc;

	public String jwtToken = null;

	@BeforeEach
	void setUp() throws Exception {
		jwtToken = Jwts.builder().setSubject("admin")
				.setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 500))
				.signWith(SignatureAlgorithm.HS512, "pksecret").compact();
	}

	@Test
	void testCreateAuthenticationToken() throws Exception {

		assertThat(jwtToken).isNotNull();
		mvc.perform(MockMvcRequestBuilders.get("/book").header("Authorization", "Bearer " + jwtToken))
				.andExpect(status().isOk());
	}

}
