package com.book.exceptions;

public class MethodArgumentCannotBeNull extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MethodArgumentCannotBeNull(String msg) {
		super(msg);
	}

}
