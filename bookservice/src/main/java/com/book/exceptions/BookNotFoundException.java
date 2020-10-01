package com.book.exceptions;

public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2000L;

	public BookNotFoundException(String title) {
		super("Book does not exist with  title name " + title);
	}
}
