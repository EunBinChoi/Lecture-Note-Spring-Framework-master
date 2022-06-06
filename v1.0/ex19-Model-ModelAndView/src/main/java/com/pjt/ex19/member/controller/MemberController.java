package com.pjt.ex19.member.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pjt.ex19.member.Member;
import com.pjt.ex19.member.service.MemberService;

@Controller
@RequestMapping("/member") 
public class MemberController {
	
	
	/* Model vs ModelAndView
	 * 1) Model: view �ܿ� �����͸� ���� (Model model, @ModelAttribute)
	 * 2) ModelAndView: view �ܿ� �����Ͷ� ���� �̸� �Բ� ����
	 * 
	 * */
	
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
	
	
	// 1) Model ��ü �̿�
//	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
//	public String memJoin(@ModelAttribute("mem") Member member) {
//		// model.addAttribute("mem", member);
//		service.memberRegister(member.getMemId(), 
//				member.getMemPw(), 
//				member.getMemMail(), 
//				member.getMemPhone());
//		
//		
//		return "/memJoinOk";
//	}
	
	// 2) ModelAndView ��ü �̿�
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
	
	// 1) Model ��ü �̿�
//	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
//	public String memLogin(@ModelAttribute("mem") Member member) {
//		Member m = service.memberSearch
//				(member.getMemId(), member.getMemPw());
//		
//		return "/memLoginOk";
//		
//	}
	
	// 2) ModelAndView ��ü �̿�
	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
	public ModelAndView memLogin(Member member) {
		Member m = service.memberSearch
				(member.getMemId(), member.getMemPw());
		
		ModelAndView mav = new ModelAndView();
		
		// ModelAndView ��ü�� ������ ����
		mav.addObject("mem", member);
		
		// ModelAndView ��ü�� ���� ���� ����
		mav.setViewName("/memLoginOk");
		
		return mav;
		
	}
}
