package com.book.exceptions;

public class UserDetailsException extends RuntimeException {

	private static final long serialVersionUID = 10023L;

	public UserDetailsException(String credentials) {
		super(credentials);
	}

}
