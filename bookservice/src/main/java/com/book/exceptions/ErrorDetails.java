package com.book.exceptions;

import lombok.Data;

@Data
public class ErrorDetails {

	private String status;
	private String errorMessage;
	private String details;

	public ErrorDetails(String status, String message, String details) {
		super();
		this.status = status;
		this.errorMessage = message;
		this.details = details;
	}
}
