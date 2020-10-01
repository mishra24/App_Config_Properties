package com.book.dto;

import lombok.Data;

@Data
public class LoggingUserDetails {

	private String username;

	private String password;

	public LoggingUserDetails() {

	}

	public LoggingUserDetails(String username, String password) {
		this.username = username;
		this.password = password;

	}

}
