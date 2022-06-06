package me.spring.file.controller;

import java.io.IOException;
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

// 여기는 고민을 더 해봐야 함
// IllegalStateException, IOException, SecurityException 추가
public class FileControllerHandler {
	private final Logger logger = LoggerFactory.getLogger(FileControllerHandler.class);


	@ExceptionHandler({BindException.class})
	public String bindException(HttpServletRequest request, HttpSession session, Exception exception) {
		System.out.println("bindException");
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
		String previousRequestURL = request.getHeader("referer");
		String cp = request.getContextPath();

		System.out.println(request.getRequestURL()); // doUpdate
		System.out.println(previousRequestURL); // update
		
		/*String[] previousRequestViewNames = {"login", "signup", "update", "delete"};
		for (String previousRequestViewName : previousRequestViewNames) {
			if(previousRequestURL.split(cp + "/member/")[1].contains(previousRequestViewName)) {
				session.setAttribute(previousRequestViewName, previousRequestViewName + "-fail");
				break;
			}
		}*/
		System.out.println("redirect:/" + previousRequestURL.split(cp + "/")[1]);
		return "redirect:/" + previousRequestURL.split(cp + "/")[1];
	}
	
	@ExceptionHandler({IllegalStateException.class, IOException.class, SecurityException.class})
	public String fileException(HttpServletRequest request, HttpSession session, Exception exception) {
		System.out.println("fileException");
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
		String previousRequestURL = request.getHeader("referer");
		String cp = request.getContextPath();

		System.out.println(request.getRequestURL()); // doUpdate
		System.out.println(previousRequestURL); // update
		
		/*String[] previousRequestViewNames = {"login", "signup", "update", "delete"};
		for (String previousRequestViewName : previousRequestViewNames) {
			if(previousRequestURL.split(cp + "/member/")[1].contains(previousRequestViewName)) {
				session.setAttribute(previousRequestViewName, previousRequestViewName + "-fail");
				break;
			}
		}*/
		return "redirect:/" + previousRequestURL.split(cp + "/")[1];
		
	}
	

	////////////////////////////// 개발자의 오류 (쿼리문의 오류) //////////////////////////////////////////
	// Database SQL에서 생길 수 있는 exception
	@ExceptionHandler({ SQLException.class })
	public String sqlException(HttpServletRequest request, SQLException  exception) {
		System.out.println("SQLException");
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
		return "error/databaseError";
	}

	// Database SQL에서 생길 수 있는 exception
	@ExceptionHandler({ DataAccessException.class })
	public String dataAccessException(HttpServletRequest request, DataAccessException  exception) {
		System.out.println("DataAccessException");
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);
		return "error/databaseError";
	}


}
