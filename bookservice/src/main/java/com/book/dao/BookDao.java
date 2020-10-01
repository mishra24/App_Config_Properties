package com.book.dao;

import java.util.List;

import com.book.dto.Book;

public interface BookDao {

	List<Book> getBooks();

	int saveBook(Book book);

	void deleteByTitle(String title);

	Book getBookByTitle(String title);

}
