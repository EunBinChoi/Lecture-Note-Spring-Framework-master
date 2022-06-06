package com.pjt.ex23.member.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pjt.ex23.member.Member;
import com.pjt.ex23.member.service.MemberService;

@Controller
@RequestMapping("/member") 
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	
	// ���� �ð� ��ȯ
	// ���� controller Ŭ���� ���� �޼ҵ尡 ����Ǹ�
	// ���������� ������ ������ �� ����
	@ModelAttribute("serverTime") 
	// model.addAttribute("serverTime", getServerTime(locale));
	public String getServerTime(Locale locale) {
		Date date = new Date();
		DateFormat dateFormat 
		= DateFormat.getDateTimeInstance
		(DateFormat.LONG, DateFormat.LONG, locale);
		
		return dateFormat.format(date);
	}

	
	@RequestMapping("/memJoinForm")
	public String memJoinForm(Member member) {
		return "/memJoinForm";
	}

	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
	public ModelAndView memJoin(Member member) {
		int result = service.memberRegister(member);
		
		ModelAndView mav = new ModelAndView();
		
		if(result == 0) {
			mav.addObject("res", "fail");
			mav.setViewName("/memJoinForm");
		}
		else {
			// ModelAndView ��ü�� ���� ���� ����
			
			mav.addObject("res", "success");
			mav.setViewName("/memJoinOk");
		}
		return mav;
	}
	
	@RequestMapping("/memLoginForm")
	public String memLoginForm(Member member) {
		return "/memLoginForm";
	}

	
	
	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
	public ModelAndView memLogin(Member member, /*HttpServletRequest request*/
			HttpSession session, HttpServletResponse response) {
		
		Member m = service.memberSearch(member);
		ModelAndView mav = new ModelAndView();
		
		
		if(m == null) {
			mav.addObject("res", "fail");
			mav.setViewName("/memLoginForm");
		}
		else {
			
			//HttpSession session = request.getSession();
			session.setAttribute("member", m); // ���� ����
			
			Cookie cookie = new Cookie("memId", m.getMemId()); // ��Ű ����
			cookie.setMaxAge(5*60); // seconds (��) ����
			response.addCookie(cookie);
			
			mav.addObject("res", "success");
			// ModelAndView ��ü�� ������ ����
			//mav.addObject("mem", member);
			
			// ModelAndView ��ü�� ���� ���� ����
			mav.setViewName("/memLoginOk");
		}
			
		
		return mav;
		
	}
	
	@RequestMapping(value="/memLogout")
	public String memLogout(Member member, /*HttpServletRequest request*/
			HttpSession session, 
			// ��Ű ���
			// memId�̶�� ��Ű�� ������ exception �߻��ϴµ�
			// ���� ��� exception �߻����� �ʰ� �Ϸ��� required=false
			@CookieValue(value="memId", required=false) Cookie cookie) {
		//HttpSession session = request.getSession();
		
	
		if(cookie != null) {
			cookie.setMaxAge(0); // ��Ű ����
		}
		
		session.invalidate(); // ���� ����
		
		return "/memLogout";
	}
	
	@RequestMapping("/memModifyForm")
	public ModelAndView memModifyForm(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		// �α����ߴ� ������ �����Ǿ��ִٸ�
		Member member = (Member)session.getAttribute("member");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("member", service.memberSearch(member));
		mav.setViewName("/memModifyForm");
		
		return mav;
	}
	
	@RequestMapping(value="/memModify", method=RequestMethod.POST)
	public ModelAndView memModify(Member member, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
			
		Member mem = service.memberModify(member);
		
		if(mem == null) {
			mav.addObject("res", "fail");
			mav.setViewName("/memModifyForm");
		}
		else {
			
			// ���� ������ ���� ���� �Ӽ��� �߰� 
			// (Ȥ�ó� �߰��� modify�� ����� ����ڿ��� ������ �� ���� �� �����ϱ�)
			session.setAttribute("member", mem);
			
			mav.addObject("res", "success");
			
			mav.addObject("memAft", mem);
			mav.setViewName("/memModifyOk");
		}
		
		return mav;
	}
	
	
	@RequestMapping("/memDeleteForm")
	public ModelAndView memDeleteForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		Member member = (Member) session.getAttribute("member");
		mav.addObject("member", member);
		mav.setViewName("/memDeleteForm");
		
		return mav;
	}
	
	@RequestMapping(value="/memDelete", method=RequestMethod.POST)
	public ModelAndView memDelete(Member member, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int result = service.memberRemove(member);
		
		if(result == 0) {
			mav.addObject("res", "fail");
			mav.setViewName("/memDeleteForm");
		}
		else {
			HttpSession session = request.getSession();
			session.invalidate();
			
			mav.addObject("res", "success");
			mav.setViewName("/memDeleteOk");
		}
		return mav;
	}
	
	
	
}
