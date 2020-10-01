package com.book.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootTest
class JwtUtilTest {

	public String jwtToken = null;

	@Autowired
	JwtUtil jwtUtil;

	@BeforeEach
	void setUp() throws Exception {
		jwtToken = Jwts.builder().setSubject("admin").setExpiration(new Date(System.currentTimeMillis() + 3600 * 30))
				.signWith(SignatureAlgorithm.HS512, "pksecret").compact();
	}

	@Test
	void testValidateToken() {
		UserDetails userDetails = new User("admin", "admin", new ArrayList<>());
		assertTrue(jwtUtil.validateToken(jwtToken, userDetails));
	}

	@Test
	void testGetUserNameFromToken() {
		assertEquals("admin", jwtUtil.getUsernameFromToken(jwtToken));

	}

	@Test
	void testGetExpireationDateFromToken() {
		Date date = jwtUtil.getExpirationDateFromToken(jwtToken);
		assertNotNull(date);
	}
}
