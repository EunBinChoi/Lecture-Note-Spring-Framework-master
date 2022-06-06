package me.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import me.spring.member.beans.MemberVO;
import me.spring.message.beans.MessageSourceVO;

@Controller
public class IndexController {
	private final Logger logger = LoggerFactory.getLogger(IndexController.class);
	private final MessageSource messageSource;
	
	
	@Autowired
	public IndexController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ModelAttribute("cp")
	public String contextPath(HttpServletRequest request) {
		System.out.println(request.getContextPath());
		return request.getContextPath();
	}

	@ModelAttribute("serverTime")
	public String serverTime(Locale locale) { // SessionLocaleResolver에서 언어 정보를 인자로 전달받음
		// Locale: 지역의 언어, 국가 등의 정보를 갖고 있는 객체 (페이지 언어 설정 가능)
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		return dateFormat.format(date);
	}
	
	@ModelAttribute("messageSource")
	public MessageSourceVO messageSourceDTO(Locale locale, HttpSession session) {
		MessageSourceVO messageSourceVO = new MessageSourceVO();
		
		
		messageSourceVO.getCommon().setHome   (messageSource.getMessage  ("common.home", null, locale));
		messageSourceVO.getCommon().setBoard  (messageSource.getMessage  ("common.board", null, locale));
		messageSourceVO.getCommon().setAlbum  (messageSource.getMessage  ("common.album", null, locale));
		messageSourceVO.getCommon().setFAQ    (messageSource.getMessage  ("common.FAQ", null, locale));
		messageSourceVO.getCommon().setHeading(messageSource.getMessage  ("common.heading", null, locale));
		messageSourceVO.getMember().setLogin  (messageSource.getMessage  ("member.login", new String[] { (String)session.getAttribute("idKey") }, locale));
		messageSourceVO.getMember().setRevise (messageSource.getMessage  ("member.revise", null, locale));
		messageSourceVO.getMember().setDelete (messageSource.getMessage  ("member.delete", null, locale));
		messageSourceVO.getMember().setLogout (messageSource.getMessage  ("member.logout", null, locale));
		messageSourceVO.getFile().setUpload   (messageSource.getMessage  ("file.upload", null, locale));
		return messageSourceVO;
	}
	////////////////////////////////////////////////////////////////////

	@RequestMapping("/")
	public String index(Model model, HttpSession session)  {
		/*
		 * 로그인 정보가 세션에 저장되어있는지 확인 (idKey 값에 저장) 
		 * 해당 페이지는 로그인하지 않으면 접근 불가능하기 때문 
		 * (이후에 interceptor로 처리 가능)
		 */
		if (session.getAttribute("idKey") == null) {
			return "redirect:/member/login";
		}

		// index.jsp의 view 단에 id를 보여주기 위함
		//MemberVO member = new MemberVO((String) session.getAttribute("idKey"));
		//model.addAttribute("member", member);
		return "index";
	}
}
