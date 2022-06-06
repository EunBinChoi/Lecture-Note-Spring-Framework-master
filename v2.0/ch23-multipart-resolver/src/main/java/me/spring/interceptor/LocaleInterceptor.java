package me.spring.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

public class LocaleInterceptor implements HandlerInterceptor {
	private final SessionLocaleResolver sessionLocaleResolver;

	@Autowired
	public LocaleInterceptor(SessionLocaleResolver sessionLocaleResolver) {
		this.sessionLocaleResolver = sessionLocaleResolver;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		// TODO Auto-generated method stub
		/* controller 실행 전에 호출 
		 * 반환값: true  -> 컨트롤러 호출 (index()) O
		 * 반환값: false -> 컨트롤러 호출 (index()) X
		 * 
		 * ex) 로그인 가능한지 확인, 세션 값 확인
		 * */
		
		/* interceptor로 처리 가능 (각 컨트롤러 호출 전에 호출되는 함수인 preHandle()) */
		if(request.getParameter("lang") != null) {
			String lang = request.getParameter("lang");
			System.out.println(lang);
			if(lang != null && !lang.equals("")) {
				sessionLocaleResolver.setLocale(request, response, new Locale(lang)); // ko, en, ja
				return true;
			}
		} 
		
		sessionLocaleResolver.setLocale(request, response, Locale.ENGLISH); // 디폴트 값
		return true;
	}
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object object, ModelAndView mav)
			throws Exception {
		// TODO Auto-generated method stub
		/* controller 실행 후에 호출 (뷰 렌더링 전)
		 * 
		 * ex) 뷰 단에 전달해야하는 model 값을 수정하거나 추가해야할 때
		 * 
		 * */
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object object, Exception exception)
			throws Exception {
		// TODO Auto-generated method stub
		/* 뷰 렌더링 후에 호출
		 * 
		 * 만약에 view 단에 전달되는 예외 처리는 못하고 view 단에 예외가 그대로 전달
		 * ex) 로그
		 * 
		 * */
	}


}
