package me.spring.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Forbidden") 
public class MemberException extends Exception {

	private static final long serialVersionUID = 1L;
	public MemberException() {}
	public MemberException(String msg) {
		super(msg);
	}
}
