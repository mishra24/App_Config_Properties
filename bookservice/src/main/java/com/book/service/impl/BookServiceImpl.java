package com.book.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.dao.BookDao;
import com.book.dto.Book;
import com.book.exceptions.BookNotFoundException;
import com.book.exceptions.MethodArgumentCannotBeNull;
import com.book.exceptions.BookAlreadyExistException;
import com.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger LOGGER = LogManager.getLogger(BookServiceImpl.class);

	@Autowired
	BookDao bookDao;

	@Override
	public List<Book> getBooks() {
		return bookDao.getBooks();

	}

	@Override
	public Book getBookByTitle(String title) {
		LOGGER.debug("Retrieve the book with title {} ", title);
		if (title.equals("null")) {
			throw new MethodArgumentCannotBeNull("please provide value instead of null");
		}
		Book bookObj = bookDao.getBookByTitle(title.toLowerCase());
		if (bookObj == null) {
			LOGGER.debug("Book does not exist with given title {} ", title);
			throw new BookNotFoundException(title);
		}

		return bookObj;

	}

	@Override
	public String save(Book book) {
		if (book == null) {
			throw new MethodArgumentCannotBeNull("please provide Book info");
		}
		Book updatedObj = bookDao.getBookByTitle(book.getTitle().toLowerCase());
		if (updatedObj != null) {
			LOGGER.debug("Book already exist in Database");
			throw new BookAlreadyExistException(updatedObj.getTitle());
		}
		bookDao.saveBook(book);
		return book.getTitle();
	}

	@Override
	public void deleteBookByTitle(String title) {
		LOGGER.debug("Getting the book before making inactive {}", title);
		if (title.equals("null")) {
			throw new MethodArgumentCannotBeNull("please provide value instead of null");
		}

		Book book = bookDao.getBookByTitle(title);
		if (book == null) {
			throw new BookNotFoundException(title);
		}
		LOGGER.debug("removing the book with title name {} ", title);
		bookDao.deleteByTitle(title);

	}

}
