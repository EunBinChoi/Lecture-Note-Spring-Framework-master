package me.spring.controller.controller;
//package me.spring.controller;
//
//import java.util.Arrays;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import me.spring.beans.Member;
//
//@Controller
//public class IndexController {
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
//		return "index";
//	}
//}
