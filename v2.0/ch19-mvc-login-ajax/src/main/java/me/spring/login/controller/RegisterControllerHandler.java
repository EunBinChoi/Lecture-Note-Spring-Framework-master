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

	// @RequestBody (ajax, json)에서 생길 수 있는 convert할 때 생길 수 있는 exception
	// checkDuplicatId() (@RequestParam), checkIdPwd() (@RequestBody) (**)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
		System.out.println("MethodArgumentNotValidException");
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
		String requestURL = request.getHeader("referer");
		String cp = request.getContextPath();
		
		// 비동기 통신을 하는 view 이름
//		if(requestURL.split(cp + "/")[1].equals("signup")) {
//			return "blank";
//		} else if(requestURL.split(cp + "/")[1].equals("update")) {
//			return "blank";
//		}
		return "blank";
	}
	
	@ExceptionHandler(BindException.class)
	public String bindException(HttpServletRequest request, HttpSession session, BindException exception) {
		System.out.println("bindException");
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
		String previousRequestURL = request.getHeader("referer");
		String cp = request.getContextPath();
		
		System.out.println(request.getRequestURL()); // doUpdate
		System.out.println(previousRequestURL); // update
		
		// 동기 통신을 하는 view 이름
//		if(previousRequestURL.split(cp + "/")[1].contains("login")) {
//			session.setAttribute("login", "login-fail");
//		} else if(previousRequestURL.split(cp + "/")[1].contains("signup")) {
//			session.setAttribute("signup", "signup-fail");
//		} else if(previousRequestURL.split(cp + "/")[1].contains("update")) {
//			session.setAttribute("update", "update-fail");
//		} else if(previousRequestURL.split(cp + "/")[1].contains("delete")) {
//			session.setAttribute("delete", "delete-fail");
//		}
		
		String[] previousRequestViewNames = {"login", "signup", "update", "delete"};
		for(int i = 0; i < previousRequestViewNames.length; i++) {
			if(previousRequestURL.split(cp + "/")[1].contains(previousRequestViewNames[i])) {
				session.setAttribute(previousRequestViewNames[i], previousRequestViewNames[i] + "-fail");
				break;
			}
		}
		return "redirect:/" + previousRequestURL.split(cp + "/")[1];
	}
//
//	// @ModelAttribute에서 생길 수 있는 exception
//	@ResponseBody
//	@ExceptionHandler({ BindException.class })
//	public String bindException(HttpServletRequest request, BindException exception) {
////		System.out.println("BindException");
////		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
////		return "register-fail";
//	}

	
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
