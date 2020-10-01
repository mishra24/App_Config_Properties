package com.book.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JwtAuthRequest {

	@ApiModelProperty(value = "Please provide valid username", required = true)
	@NotNull(message = "Please provide valid username")
	@NotEmpty(message = "Please provide valid username")
	private String username;

	@ApiModelProperty(value = "Please provide valid password", required = true)
	@NotNull(message = "Please provide valid password")
	@NotEmpty(message = "Please provide valid password")
	private String password;

	public JwtAuthRequest() {

	}

	public JwtAuthRequest(String userName, String password) {
		this.username = userName;
		this.password = password;
	}
}
