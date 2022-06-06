package me.spring.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found") // 404
public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	public NotFoundException() {}
	public NotFoundException(String msg) {
		super(msg);
	}
}
