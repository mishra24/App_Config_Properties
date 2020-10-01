package com.book.service;

import java.util.List;

import com.book.dto.Book;

public interface BookService {

	List<Book> getBooks();

	String save(Book book);

	void deleteBookByTitle(String title);

	Book getBookByTitle(String title);

}
