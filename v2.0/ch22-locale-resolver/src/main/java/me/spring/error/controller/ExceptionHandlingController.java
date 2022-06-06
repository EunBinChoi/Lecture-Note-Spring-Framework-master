package me.spring.error.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import me.spring.error.exception.BadRequestException;
import me.spring.error.exception.InternalServerException;
import me.spring.error.exception.NotFoundException;
import me.spring.error.exception.RegisterException;

@Controller
public class ExceptionHandlingController {
	private final Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

	// 예외 핸들러
	@ExceptionHandler(BadRequestException.class)
	public ModelAndView badRequest(HttpServletRequest request, Exception exception) {
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL());
		mav.addObject("cp",  request.getContextPath());
		mav.setViewName("error/400");
		return mav;
	}

	@ExceptionHandler(NotFoundException.class)
	public ModelAndView notFound(HttpServletRequest request, Exception exception) {
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL());
		mav.addObject("cp",  request.getContextPath());
		mav.setViewName("error/404");
		return mav;
	}

	@ExceptionHandler(InternalServerException.class)
	public ModelAndView serverError(HttpServletRequest request, Exception exception) {
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL());
		mav.addObject("cp",  request.getContextPath());
		mav.setViewName("error/500");
		return mav;
	}


	/* Test를 위함 (RequestMapping 값을 수정해서 잘 나오는지 확인) */
	/////////////// AnnotationMethoHanlderExceptionResolver (@ExceptionHanlder) ////////////////////////////////////////////////
	@RequestMapping("error400")
	public String handleError400() throws Exception {
		throw new BadRequestException("400");
	}
	@RequestMapping("error404")
	public String handleError404() throws Exception {
		throw new NotFoundException("404");
	}
	@RequestMapping("error500")
	public String handleError500() throws Exception {
		throw new InternalServerException("500");
	}
	//////////////////////////////////////////////////////////////////////////////////////
	
	/////////////// ResponseStatusExceptionResolver (@ResponseStatus) //////////////////////
	@RequestMapping("error")
	public String handleError() throws Exception {
		throw new RegisterException(); // 403 
	}
	/////////////////////////////////////////////////////////////////////////////////////
	
	///////////// SimpleMappingExceptionResolver //////////////////////////////////////////
	@RequestMapping("nullError") // .../cp/nullError
	public String nullError() throws Exception {
		throw new NullPointerException(); 
	}
	@RequestMapping("arrayBoundsError") 
	public String arrayBoundsError() throws Exception {
		throw new ArrayIndexOutOfBoundsException(); 
	}
	@RequestMapping("arithmeticError")
	public String arithmeticError() throws Exception {
		throw new ArithmeticException(); 
	}
	@RequestMapping("generalError")
	public String generalerror() throws Exception {
		throw new Exception(); 
	}
	/////////////////////////////////////////////////////////////////////////////////////
}
