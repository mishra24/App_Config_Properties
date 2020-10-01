package com.book.advice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.book.exceptions.BookNotFoundException;
import com.book.exceptions.ErrorDetails;
import com.book.exceptions.JwtTokenException;
import com.book.exceptions.MethodArgumentCannotBeNull;
import com.book.exceptions.UserDetailsException;
import com.book.exceptions.BookAlreadyExistException;
import com.book.util.APIResponse;
import com.book.util.CommonUtils;

@ControllerAdvice
public class BookExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LogManager.getLogger(BookExceptionHandlerAdvice.class);

	@ResponseBody
	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public APIResponse<Void> bookExceptionHandler(BookNotFoundException ex) {

		return CommonUtils.getFailureResponse(null, "BookNotFound", ex.getMessage());

	}

	@ResponseBody
	@ExceptionHandler(BookAlreadyExistException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public APIResponse<Void> bookExistExceptionHandler(BookAlreadyExistException ex) {

		return CommonUtils.getFailureResponse(null, "DuplicateBook", ex.getMessage());

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOG.info("Validation Failed");

		ErrorDetails errorDetails = new ErrorDetails("Failed", "Validation Failed",
				ex.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(JwtTokenException.class)
	protected ResponseEntity<Object> jwtExceptionHandler(JwtTokenException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(UserDetailsException.class)
	protected ResponseEntity<Object> userDetailsHandler(UserDetailsException ex) {
		LOG.info("Bad Credentials");
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentCannotBeNull.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected APIResponse<Void> methodArgumentHandler(MethodArgumentCannotBeNull ex) {
		return CommonUtils.getFailureResponse(null, String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public APIResponse<Void> exception(Exception ex) {
		return CommonUtils.getFailureResponse(null, "ServiceNotAvailable", "ServiceNotAvailable");

	}
}
