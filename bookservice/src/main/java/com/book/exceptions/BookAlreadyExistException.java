package com.book.exceptions;

public class BookAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1000L;

	public BookAlreadyExistException(String title) {
		super("Book already exist with title - " + title);
	}

}
