package me.spring.ajaxse.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class UserControllerExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(UserControllerExceptionHandler.class);
	
	////////////////////////////// 사용자의 입력오류 //////////////////////////////////////////
	// @RequestBody에서 생길 수 있는 exception
	@ResponseBody
	// @ResponseStatus(HttpStatus.BAD_REQUEST) // 응답 상태를 400로 만듦
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public String methodArgumentNotValidException(HttpServletRequest request,
			MethodArgumentNotValidException exception) {
		System.out.println("MethodArgumentNotValidException");
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
		return "register-fail";
	}

	// @ModelAttribute에서 생길 수 있는 exception
	@ResponseBody
	// @ResponseStatus(HttpStatus.BAD_REQUEST) // 응답 상태를 400로 만듦
	@ExceptionHandler({ BindException.class })
	public String bindException(HttpServletRequest request, BindException exception) {
		System.out.println("BindException");
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
		return "register-fail";
	}

//	@ResponseBody
//	// @ResponseStatus(HttpStatus.BAD_REQUEST) // 응답 상태를 400로 만듦
//	@ExceptionHandler({ UnexpectedTypeException.class })
//	public String unexpectedTypeException(HttpServletRequest request, UnexpectedTypeException exception) {
//		System.out.println("UnexpectedTypeException");
//		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
//		return "register-fail";
//	}

	////////////////////////////// 개발자의 오류 (쿼리문의 오류) //////////////////////////////////////////
	// Database SQL에서 생길 수 있는 exception
	@ExceptionHandler({ SQLException.class })
	public void sqlException(HttpServletRequest request, SQLException  exception) {
		System.out.println("SQLException");
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
	}
	
	// Database SQL에서 생길 수 있는 exception
	@ExceptionHandler({ DataAccessException.class })
	public void dataAccessException(HttpServletRequest request, DataAccessException  exception) {
		System.out.println("DataAccessException");
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
	}
	
	
//	@ResponseBody
//	//@ResponseStatus(HttpStatus.BAD_REQUEST) // 응답 상태를 400로 만듦
//	@ExceptionHandler({ ConstraintViolationException.class })
//	public String constraintViolationException(HttpServletRequest request, MethodArgumentNotValidException  exception) {
//		System.out.println("constraintViolationException");
//		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
//		return  "register-fail";
//	}

}
