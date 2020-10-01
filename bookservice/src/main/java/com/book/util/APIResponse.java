package com.book.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class APIResponse<T> {

	private T response;
	private String status;
	private String errorCode;
	private String errorMessage;

}