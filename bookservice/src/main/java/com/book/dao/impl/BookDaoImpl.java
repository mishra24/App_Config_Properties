package com.book.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.book.dao.BookDao;
import com.book.dto.Book;

@Repository
public class BookDaoImpl implements BookDao {

	private static final Logger LOGGER = LogManager.getLogger(BookDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Book> getBooks() {
		try {
			LOGGER.debug("Get all books from DB");
			List<Book> bookList;
			bookList = jdbcTemplate.query("select title,author from book where active=1 order by title ASC",
					new BeanPropertyRowMapper<Book>(Book.class));
			return bookList;
		} catch (DataAccessException ex) {
			LOGGER.error("Book was not found");
			return new ArrayList<>();
		}

	}

	@Override
	public Book getBookByTitle(String title) {
		try {
			Book book;
			LOGGER.debug("Retrieve the book from DB with title {} ", title);
			book = jdbcTemplate.queryForObject("select title,author from book where title=? and active=?",
					new Object[] { title, 1 }, new BeanPropertyRowMapper<Book>(Book.class));
			return book;
		} catch (DataAccessException ex) {
			LOGGER.error("Book not found in Database");
			return null;
		}
	}

	@Override
	public int saveBook(Book book) {
		int active = 1;
		try {
			LOGGER.debug("save the book with title {}  ", book.getTitle());
			return jdbcTemplate.update("INSERT INTO BOOk(title, author,active) VALUES(?,?,?)",
					book.getTitle().toLowerCase(), book.getAuthor().toLowerCase(), active);
		} catch (DataAccessException ex) {
			LOGGER.error("Exception while saving the book");
			return 0;
		}

	}

	@Override
	public void deleteByTitle(String title) {
		LOGGER.debug("making record in active with given title {}", title);
		jdbcTemplate.update("update Book set active=0 where title=?", title);

	}

}
