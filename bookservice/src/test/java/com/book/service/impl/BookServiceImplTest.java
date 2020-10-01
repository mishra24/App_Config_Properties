package com.book.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.book.dao.BookDao;
import com.book.dto.Book;
import com.book.exceptions.BookAlreadyExistException;
import com.book.exceptions.BookNotFoundException;
import com.book.exceptions.MethodArgumentCannotBeNull;

@SpringBootTest
class BookServiceImplTest {

	@InjectMocks
	BookServiceImpl bookService;

	@Mock
	BookDao bookDao;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	final void testGetBooks() {

		List<Book> bookList = new ArrayList<Book>();
		Book book1 = new Book("Myhabit", "Robert");
		Book book2 = new Book("dignity of life", "stalone");
		bookList.add(book1);
		bookList.add(book2);
		when(bookDao.getBooks()).thenReturn(bookList);
		List<Book> books = bookService.getBooks();
		System.out.println(books.size());
		assertEquals(2, books.size());

	}

	@Test
	final void testGetBookByTitle() {
		Book book = new Book("Kalam", "Bluewind");
		book.setAuthor("Kalam");
		book.setTitle("Bluewind");
		when(bookDao.getBookByTitle(anyString())).thenReturn(book);
		Book bookObj = bookService.getBookByTitle("bluewind");
		assertNotNull(bookObj);
		assertEquals("Bluewind", bookObj.getTitle());

	}

	@Test
	final void testSave() {
		Book book = new Book("ChentanBhagath", "Mitti");
		when(bookDao.saveBook(any(Book.class))).thenReturn(1);
		String title = bookService.save(book);
		assertNotNull(title);
		assertEquals("ChentanBhagath", title);
	}

	@Test
	final void testGetBookByTitle_BookNotFoundExceptions() {
		when(bookDao.getBookByTitle(anyString())).thenReturn(null);
		assertThrows(BookNotFoundException.class, () -> {
			bookService.getBookByTitle("shiva");
		});

	}

	@Test
	final void testSave_BookAlreadyExistException() {
		Book book = new Book("amazon", "JungleBook");
		when(bookDao.getBookByTitle(anyString())).thenReturn(book);
		assertThrows(BookAlreadyExistException.class, () -> {
			bookService.save(book);
		});

	}

	@Test
	final void testgetBookWithNullValue() {
		Exception exception = assertThrows(MethodArgumentCannotBeNull.class, () -> {
			bookService.getBookByTitle("null");
		});

		assertTrue(exception.getMessage().contains("please provide value instead of null"));

	}

	@Test
	final void testDeleteBookWithNullValue() {
		Exception exception = assertThrows(MethodArgumentCannotBeNull.class, () -> {
			bookService.deleteBookByTitle("null");
		});
		assertTrue(exception.getMessage().contains("please provide value instead of null"));

	}

	@Test
	final void testSaveBookWithNullValue() {
		Exception exception = assertThrows(MethodArgumentCannotBeNull.class, () -> {
			bookService.save(null);
		});
		assertTrue(exception.getMessage().contains("please provide Book info"));

	}

}
