package me.spring.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error") // 500
public class InternalServerException extends Exception {

	private static final long serialVersionUID = 1L;
	public InternalServerException() {}
	public InternalServerException(String msg) {
		super(msg);
	}
}
