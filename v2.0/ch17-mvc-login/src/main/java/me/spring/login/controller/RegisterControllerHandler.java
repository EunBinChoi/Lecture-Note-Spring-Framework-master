package me.spring.login.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public class RegisterControllerHandler {
	private final Logger logger = LoggerFactory.getLogger(RegisterControllerHandler.class);

	
	//////////////////////////////개발자의 오류 (쿼리문의 오류) //////////////////////////////////////////
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
	
	
}
