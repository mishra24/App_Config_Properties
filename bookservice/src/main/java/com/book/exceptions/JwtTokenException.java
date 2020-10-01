package com.book.exceptions;

public class JwtTokenException extends RuntimeException {

	private static final long serialVersionUID = -920982958274699773L;

	public JwtTokenException(String msg) {
		super(msg);
	}

}
