package com.book.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.constants.BookServiceConstants;
import com.book.dto.Book;
import com.book.service.BookService;
import com.book.util.APIResponse;
import com.book.util.CommonUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/book")
@Validated
public class BookController {

	private static final Logger LOGGER = LogManager.getLogger(BookController.class);

	@Autowired
	BookService bookService;

	@ApiOperation(value = "View list of available books", response = Book.class)
	@ApiResponses(value = {
			@ApiResponse(response = APIResponse.class, code = 200, message = BookServiceConstants.SUCCESSFULLY_RETRIEVED),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
	@GetMapping(produces = "application/json")
	public APIResponse<List<Book>> getBooks() {
		LOGGER.debug("start retrieving the books");
		List<Book> books = bookService.getBooks();
		LOGGER.debug("successfully retrieve the books ");

		return CommonUtils.getSuccessResponse(books);
	}

	@ApiOperation(value = "save the Book ", response = Book.class)
	@ApiResponses(value = {
			@ApiResponse(response = APIResponse.class, code = 200, message = BookServiceConstants.SUCCESSFULLY_CREATED),
			@ApiResponse(code = 400, message = BookServiceConstants.NOT_SAVED) })
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
	@PostMapping(value = "/{savebook}", produces = "application/json")
	public APIResponse<Void> saveBook(
			@ApiParam(value = "book Title to retrieve book", required = true) @Valid @RequestBody Book book) {

		LOGGER.debug("start the processing of saving book with title name {} ", book.getTitle());
		bookService.save(book);
		LOGGER.debug("Book save successfully with {} ", book.getTitle());

		return CommonUtils.getSuccessResponse(null);

	}

	@ApiOperation(value = "View book", notes = "retrieve book with the given title", response = Book.class)
	@ApiResponses(value = {
			@ApiResponse(response = APIResponse.class, code = 200, message = BookServiceConstants.SUCCESSFULLY_RETRIEVED),
			@ApiResponse(code = 404, message = BookServiceConstants.NOT_FOUND) })
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
	@GetMapping(value = "/{bookTitle}", produces = "application/json")
	public APIResponse<Book> getBookByTitle(
			@ApiParam(value = "book Title to retrieve book", required = true) @PathVariable @NotNull String bookTitle) {

		LOGGER.debug("start retrieve of book with title - {}", bookTitle);
		Book book = bookService.getBookByTitle(bookTitle);
		LOGGER.debug("end of  book retrieval with title -{} ", bookTitle);

		return CommonUtils.getSuccessResponse(book);
	}

	@ApiOperation(value = "Remove book", notes = "delete book with the given title", response = Book.class)
	@ApiResponses(value = {
			@ApiResponse(response = APIResponse.class, code = 200, message = "Successfully deleted the resources"),
			@ApiResponse(code = 404, message = BookServiceConstants.NOT_FOUND) })
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
	@DeleteMapping(value = "/{bookTitle}", produces = "application/json")
	public APIResponse<Void> deleteBookByTitle(
			@ApiParam(value = "book Title to delete book", required = true) @PathVariable(required = true) String bookTitle) {

		LOGGER.debug("start removing of book with given title -{} ", bookTitle);
		bookService.deleteBookByTitle(bookTitle);
		LOGGER.debug("end of  removing book with given title - {}", bookTitle);

		return CommonUtils.getSuccessResponse(null);
	}
}
