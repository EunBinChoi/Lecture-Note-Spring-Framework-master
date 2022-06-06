package me.spring.controller.controller;
//package me.spring.controller;
//
//import java.text.DateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import me.spring.beans.Member;
//
//@Controller
//public class IntegratedController {
//	
//	// 해당 클래스 내에서 공통적으로 호출되는 메소드
//	// 컨트롤러 내에서 공통적으로 사용하는 ModelAttribute을 지정
//	@ModelAttribute("cp")
//	public String getContextPath(HttpServletRequest request) {
//		return request.getContextPath();
//	}
//	@ModelAttribute("serverTime")
//	public String getContextPath(Locale locale) {
//		Date date = new Date();
//		DateFormat dateFormat 
//		= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		return dateFormat.format(date);
//	}
//	///////////////////////////////////////////////////////
//	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Model model) {
//		model.addAttribute("key", "home");
//		return "home"; // view
//	}
//	
//	@RequestMapping("/index") // 디폴트가 GET 방식
//	public String index(Model model, HttpServletRequest request) {
////		// 1. model에 저장하는 객체가 String
////		model.addAttribute("key", "index");
////		
////		// 2. model에 저장하는 객체가 Member
////		Member member = new Member("a", "a1234");
////		model.addAttribute("member", member);
////		
////		// 3. model에 저장하는 객체가 List<Member>
////		List<Member> lists = Arrays.asList(
////				new Member("a", "a1234"),
////				new Member("b", "b1234"),
////				new Member("c", "c1234"));
////		model.addAttribute("members", lists);
//		
//		// 4. model object -> request object
//		request.setAttribute("key", "index");
//		
//		Member member = new Member("a", "a1234");
//		request.setAttribute("member", member);
//		
//		List<Member> lists = Arrays.asList(
//				new Member("a", "a1234"),
//				new Member("b", "b1234"),
//				new Member("c", "c1234"));
//		request.setAttribute("members", lists);
//		
//		return "index"; // view
//	}
//}
