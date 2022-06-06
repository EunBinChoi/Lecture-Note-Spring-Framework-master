package me.spring.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request") // 400
public class BadRequestException extends Exception {

	private static final long serialVersionUID = 1L;
	public BadRequestException() {}
	public BadRequestException(String msg) {
		super(msg);
	}
}
