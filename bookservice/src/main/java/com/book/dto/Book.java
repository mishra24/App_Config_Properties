package com.book.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_DEFAULT)
public class Book {

	@ApiModelProperty(notes = "The database generated book ID", hidden = true)
	private Long id;

	@ApiModelProperty(value = "Please provide title name", required = true)
	@NotNull
	@NotEmpty(message = "Please provide a title")
	private String title;

	@ApiModelProperty(value = "author name", required = true)
	@NotNull
	@NotEmpty(message = "Please provide a author")
	private String author;

	@ApiModelProperty(hidden = true)
	private int active;
	@ApiModelProperty(hidden = true)
	private String createTime;
	@ApiModelProperty(hidden = true)
	private String updateTime;

	public Book() {

	}

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

}
