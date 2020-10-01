package com.book.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import com.book.BookserviceApplication;
import com.book.config.H2ProfileConfigTest;
import com.book.dao.impl.BookDaoImpl;
import com.book.dto.Book;

@SpringBootTest(classes = { BookserviceApplication.class, H2ProfileConfigTest.class })
class BookDaoTest {

	private static final Logger LOGGER = LogManager.getLogger(BookDaoTest.class);

	@InjectMocks
	BookDaoImpl bookDao;

	@InjectMocks
	private JdbcTemplate jdbcTemplate;

	@Mock
	private DataSource dataSource;

	private Book book;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("schema.sql")
				.addScript("data.sql").build();
//		bookDao = new BookDaoImpl();
		bookDao.setDataSource(dataSource);
		book = new Book("Arjun", "Ramesh");
		book.setActive(1);
	}

	@Test
	void testGetBooks() {
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book);

		bookDao.saveBook(book);
		List<Book> list = bookDao.getBooks();
		System.out.println(list);
		assertNotNull(bookDao.getBooks());
		assertEquals(2, list.size());

	}

	@Test
	void testSaveBook() {
		book.setTitle("Wisdom");
		book.setAuthor("Kalam");
		book.setActive(1);
		bookDao.saveBook(book);
		assertEquals("wisdom", (bookDao.getBooks().get(1).getTitle()));

	}

}
