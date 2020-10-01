package com.book.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.book.dao.BookDao;
import com.book.dto.Book;
import com.book.service.impl.BookServiceImpl;
import com.book.util.APIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BookControllerTest {

	@InjectMocks
	BookController bookController;

	@Mock
	BookServiceImpl bookService;

	@Autowired
	BookDao bookDao;

	@Autowired
	private MockMvc mockMvc;

	public Book book;

	private final ObjectMapper mapper = new ObjectMapper();

	private static ValidatorFactory validatorFactory;
	private static Validator validator;

	private final String title = "Truth";

	public String jwtToken = null;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		jwtToken = Jwts.builder().setSubject("admin")
				.setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 500))
				.signWith(SignatureAlgorithm.HS512, "pksecret").compact();
		book = new Book("Truth", "Indira");
		book.setTitle("Truth");
		book.setAuthor("indira");

	}

	@Test
	void testGetBooks() {
		List<Book> bookList = new ArrayList<Book>();
		Book book1 = new Book("Daffodil sky", "Bates");
		Book book2 = new Book("white tiger", "Arvind");
		bookList.add(book1);
		bookList.add(book2);
		when(bookService.getBooks()).thenReturn(bookList);
		APIResponse<List<Book>> books = bookController.getBooks();
		assertEquals(2, books.getResponse().size());
	}

	@Test
	void testSaveBook() {
		Book book = new Book("hindswaraj", "Mahatma Gandhi");
		when(bookService.save(any(Book.class))).thenReturn("hindswaraj");
		APIResponse<Void> bookresponse = bookController.saveBook(book);
		assertThat(bookresponse.getStatus()).isEqualTo("SUCCESS");
	}

	@Test
	void testGetBookByTitle() {
		when(bookService.getBookByTitle(anyString())).thenReturn(book);
		APIResponse<Book> response = bookController.getBookByTitle(title);
		assertNotNull(response);
		assertEquals(title, response.getResponse().getTitle());

	}

	@Test
	final void testInputValidation() {
		Book book = new Book("Biglie", "roy");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertTrue(violations.isEmpty());

	}

	@Test
	final void testUnAuthorisedAccess() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(401, response.getStatus());

	}

	@Test
	final void testAuthTokenVaidation() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book")
				.header("Authorization", "Bearer " + jwtToken).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());

	}

	@Test
	final void testCreateRetrieveBook() throws Exception {
		Book book = new Book("BallWithRing", "albert");
		mockMvc.perform(post("/book/savebook").header("Authorization", "Bearer " + jwtToken)
				.contentType("application/json").content(mapper.writeValueAsString(book))).andExpect(status().isOk());

		Book result = bookDao.getBookByTitle("BallWithRing");
		assertEquals(result.getTitle(), book.getTitle().toLowerCase());

	}

	@Test
	final void testSaveBookWithNullValue() throws Exception {
		Book book = new Book(null, "kalam");
		mockMvc.perform(post("/book/savebook").header("Authorization", "Bearer " + jwtToken)
				.contentType("application/json").content(mapper.writeValueAsString(book)))
				.andExpect(status().is4xxClientError());

	}

	@Test
	final void testSaveBookWithEmpty() throws Exception {
		Book book = new Book("", "kalam");
		mockMvc.perform(post("/book/savebook").header("Authorization", "Bearer " + jwtToken)
				.contentType("application/json").content(mapper.writeValueAsString(book)))
				.andExpect(status().is4xxClientError());

	}

	@Test
	final void testTitleInputNullValidation() {
		Book book = new Book(null, "roy");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations.size()).isEqualTo(2);
	}

	@Test
	final void testAuthorInputNullValidation() {
		Book book = new Book("Ganga", null);
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations.size()).isEqualTo(2);
	}

	@Test
	final void whenEmptytitle_thenNoConstraintViolations() {
		Book book = new Book("", "roy");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations.size()).isEqualTo(1);
	}

	@Test
	final void whenEmpty_thenConstraintViolations() {
		Book book = new Book("", "");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations.size()).isEqualTo(2);
	}

}