package com.pjt.ex16;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */

/*
 * 1) ����� ��û
 * http://localhost:8000/ex16/login
 * 
 * 2) Controller Ŭ���� �˻� (@Controller)
 * 
 * 3) RequestMapping ���� �´� �޼��� ����
 * (����� ��û�� �´� �޼��� ����)
 * 
 * 4) ViewResolver�� ���ؼ�
 *     /WEB-INF/views/login.jsp ���� �˻� �� ���� 
 *     (servlet-context.xml ���Ͽ� ����)
 *     
 * 5) login.jsp �� ����ڿ��� ���� (view)
 *
 * => �Ϸ��� ������ DispatcherServlet ����
 * 
 * */


@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//@RequestMapping(value = "/", method = RequestMethod.GET)
	// �⺻���� get�̱� ������ method ���� ����
	// @RequestMapping �Ӽ����� �ϳ��� ��쿡�� value (�Ӽ� �̸�) ���� ����
	@RequestMapping("/loginSuccess")
	public String loginSuccess(Model model) {
		
		// model�� ���� view�� ����� �����͸� ����
		model.addAttribute("loginKey", "loginValue" );
		
		return "loginSuccess"; // loginSuccess.jsp ȣ��
	}
	
	@RequestMapping("/loginFail")
	public String loginFail(Model model) {
		
		// model�� ���� view�� ����� �����͸� ����
		model.addAttribute("loginKey", "loginValue" );
		
		return "loginFail"; // loginFail.jsp ȣ��
	}
	
}
