package me.spring.controller.controller;
//package me.spring.controller;
//
//import java.text.DateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
///**
// * Handles requests for the application home page.
// */
//@Controller
//public class HomeController {
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Model model) {
//		model.addAttribute("key", "home");
//		return "home";
//	}
//	
//	/*	URI / URL 작성 규칙
//	 *  1) 동사보다는 명사를 사용 (명사: 정보의 자원 명사로 표기)
//	 *  ex) GET /cp/getUsers/1 (X) --> id가 1번인 user를 검색
//	 *  ex) GET /cp/users/1    (O) --> id가 1번인 user를 검색
//	 *  
//	 *  2) 자원에 대한 행위 (동사)는 HTTP METHOD (GET/POST/DELETE/PUT)를 사용
//	 *  GET    /cp/users/1 --> id가 1번인 user를 조회
//	 *  PUT    /cp/users/1 --> id가 1번인 user를 수정
//	 *  DELETE /cp/users/1 --> id가 1번인 user를 삭제
//	 *  POST   /cp/users/1 --> id가 1번인 user를 삽입
//	 *  
//	 *  3) 단수 명사가 아닌 복수형을 사용
//	 *  
//	 *  (*) 여기서 1은 DB의 PK 역할을 하는 값 (URI에 노출시켜도 되는 값)
//	 * 
//	 * ===============================================================
//	 *  URI: Uniform Resource Identifier
//	 *  URL: Uniform Resource Locator
//	 *  
//	 *  ex) 사람 정보: 이름 (URI), 주소 (URI, URL)
//	 *  
//	 *  URI > URL
//	 *  
//	 *  https://www.naver.com/index.jsp --> URI, URL
//	 *  https://www.naver.com/index     --> URI
//	 *  request mapping 값을 안다고 해서 view page의 위치는 모름
//	 *  
//	 *  client에게는 request mapping 값만 알도록 즉, 가상의 경로만 알려줌 (URI)
//	 *  		   실제 view page의 위치 (URL)는 숨김
//	 * */
//	
//}
