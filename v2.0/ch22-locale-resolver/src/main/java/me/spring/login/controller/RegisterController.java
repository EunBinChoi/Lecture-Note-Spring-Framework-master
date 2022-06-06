package me.spring.login.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import me.spring.login.message.beans.MessageSourceVO;

@Controller
public class RegisterController extends RegisterControllerHandler {
	private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	@Autowired private MessageSource messageSource;
	
//	@Autowired
//	public RegisterController(MessageSource messageSource) {
//		this.messageSource = messageSource;
//	}
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

    @ModelAttribute("serverTime")
	public String getServerTime(Locale locale) { // SessionLocaleResolver에서 언어 정보를 인자로 전달받음
		// Locale: 지역의 언어, 국가 등의 정보를 갖고 있는 객체 (페이지 언어 설정 가능)
    	Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		return dateFormat.format(date);
	}
    
    @ModelAttribute("messageSource")
    public MessageSourceVO messageSource(Locale locale) {
    	/* Locale locale: 현재 서버의 Locale 값을 인자로 전달 받음 */
    	MessageSourceVO messageSourceDTO = new MessageSourceVO();
		messageSourceDTO.setHome (messageSource.getMessage("home", null, locale));
		messageSourceDTO.setBoard(messageSource.getMessage("board", null, locale));
		messageSourceDTO.setAlbum(messageSource.getMessage("album", null, locale));
		messageSourceDTO.setFAQ  (messageSource.getMessage("FAQ", null, locale));
		messageSourceDTO.setHeading(messageSource.getMessage("heading", null, locale));
		messageSourceDTO.setLoginMessage(messageSource.getMessage("loginMessage", new String[] { "sally" }, locale));
		messageSourceDTO.setReviseMember(messageSource.getMessage("reviseMember", null, locale));
		messageSourceDTO.setDeleteMember(messageSource.getMessage("deleteMember", null, locale));
		messageSourceDTO.setLogout(messageSource.getMessage("logout", null, locale));
		return messageSourceDTO;
    }
	////////////////////////////////////////////////////////////////////

	@RequestMapping("/")
	public String index()  {
		return "index";
	}
	//////////////////////////////////////////////////////////////////////////

	

}
