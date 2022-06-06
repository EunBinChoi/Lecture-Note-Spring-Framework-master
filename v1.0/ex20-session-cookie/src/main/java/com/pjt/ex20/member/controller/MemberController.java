package com.pjt.ex20.member.controller;

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

import com.pjt.ex20.member.Member;
import com.pjt.ex20.member.service.MemberService;

@Controller
@RequestMapping("/member") 
public class MemberController {
	

	
	@Autowired
	MemberService service;
	
	
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
	

	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
	public ModelAndView memJoin(Member member) {
		service.memberRegister(member.getMemId(), 
				member.getMemPw(), 
				member.getMemMail(), 
				member.getMemPhone());
		
		ModelAndView mav = new ModelAndView();
		
		// ModelAndView ��ü�� ������ ����
		mav.addObject("mem", member);
		
		// ModelAndView ��ü�� ���� ���� ����
		mav.setViewName("/memJoinOk");
		
		return mav;
	}
	
	/* session ����
	 * 1) HttpServletRequest request -> request.getSession()
	 * 2) HttpSession session
	 * 
	 * */
	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
	public ModelAndView memLogin(Member member, /*HttpServletRequest request*/
			HttpSession session, HttpServletResponse response) {
		
		Member m = service.memberSearch
				(member.getMemId(), member.getMemPw());
		
		ModelAndView mav = new ModelAndView();
		
		// id: d, pass: d
		// dbMap.put(id) -> member
		// ���� id�� �����ϴµ� pass�� �ٸ� ȸ���� ã�Ƴ� ���� ����
		try {
			if(!(m.getMemId().equals(member.getMemId()))
					|| !(m.getMemPw().equals(member.getMemPw()))) {
				mav.setViewName("/memLoginFail");
			}
			else {
				
				//HttpSession session = request.getSession();
				session.setAttribute("member", m); // ���� ����
				
				Cookie cookie = new Cookie("memId", m.getMemId()); // ��Ű ����
				cookie.setMaxAge(5*60); // seconds (��) ����
				response.addCookie(cookie);
				
				// ModelAndView ��ü�� ������ ����
				mav.addObject("mem", member);
				
				// ModelAndView ��ü�� ���� ���� ����
				mav.setViewName("/memLoginOk");
			}
		}
		catch(NullPointerException e) {
			mav.setViewName("redirect:/"); // main �������� redirect
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
}
