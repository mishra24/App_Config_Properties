package com.book.dto;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private final String jwtToken;

	public JwtAuthResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
