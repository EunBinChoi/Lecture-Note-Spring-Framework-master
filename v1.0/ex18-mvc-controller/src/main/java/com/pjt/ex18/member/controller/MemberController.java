package com.pjt.ex18.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pjt.ex18.member.Member;
import com.pjt.ex18.member.service.MemberService;

@Controller
@RequestMapping("/member") 
// controller �޼ҵ� �߿��� 
// requestMapping�� �ߺ��Ǵ� ���� ������
// controller class �� �κп� �� �� ����
public class MemberController {
	
	/* http ����� ���� ��������� ������ ���޹޴� ���
	 * 1) HttpServletRequest request -> memLogin()
	 * 2) @RequestParam  -> memLogin()
	 * 3) Member Ŭ���� ��ü �̿� (command ��ü) -> memJoin()
	 * */
	
	
	//@Resource(name="memService")
	@Autowired
	MemberService service;
	
	// 1. ȸ������ �޼ҵ�
//	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
//	public String memJoin(Model model, HttpServletRequest request) {
//		
//		// memJoin.html���� ����ڰ� �Է��� ������
//		String memId = request.getParameter("memId");
//		String memPw = request.getParameter("memPw");
//		String memMail = request.getParameter("memMail");
//		String memPhone1 = request.getParameter("memPhone1");
//		String memPhone2 = request.getParameter("memPhone2");
//		String memPhone3 = request.getParameter("memPhone3");
//		
//		
//		// service ��ü�� ���ؼ� ȸ������
//		service.memberRegister(memId, memPw, memMail, 
//				memPhone1, memPhone2, memPhone3);
//		
//		// memJoinOk.jsp (view)�� �����ֱ� ���ؼ� model ��ü�� ���
//		model.addAttribute("memId", memId);
//		model.addAttribute("memPw", memPw);
//		model.addAttribute("memMail", memMail);
//		model.addAttribute("memPhone1", memPhone1);
//		model.addAttribute("memPhone2", memPhone2);
//		model.addAttribute("memPhone3", memPhone3);
//		
//		return "/memJoinOk";
//	}
	
	// 3) command ��ü �̿� 
	// + @ModelAttribute (��ü�� ��Ī ������)
	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
	public String memJoin(@ModelAttribute("mem") Member member) { 
		// ����� ��û�� ���� �����͸� ���� setter ȣ��
		// setMemId(), setMemPw(), setMemMail()........
		// Member member: ��������� ���޹��� �Ķ���� ���� ������ �ִ� ��ü
		// (������ �����̳ʿ� ���� (controller, view ��� ����))
		
		// service ��ü�� ���ؼ� ȸ������
		service.memberRegister(member.getMemId(), 
				member.getMemPw(), 
				member.getMemMail(), 
				member.getMemPhone());
		
		
		return "/memJoinOk";
	}
	
	// 2. �α��� �޼ҵ�
	// 1) HttpServletRequest request
//	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
//	public String memLogin(Model model, HttpServletRequest request) {
//		
//		String memId = request.getParameter("memId");
//		String memPw = request.getParameter("memPw");
//		
//		Member member = service.memberSearch(memId, memPw);
//		
//		model.addAttribute("memId", member.getMemId());
//		model.addAttribute("memPw", member.getMemPw());
//		
//		return "/memLoginOk";
//		
//	}
	
	// 2) @RequestParam
	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
	public String memLogin(Model model, /*HttpServletRequest request*/
			@RequestParam("memId") String memId,
			@RequestParam(value="memPw", required=false, defaultValue="1234") 
	String memPw) {
		// ����� ��û�� ������ ������ exception �߻�
		// required=false: ����� ��û�� ������ �ʾƵ� exception �߻� X
		// defaultValue="1234": ����� ��û�� ������ �Ķ���Ͱ� ���� ��� ����Ʈ �� ����
		
		
		//String memId = request.getParameter("memId");
		//String memPw = request.getParameter("memPw");
		
		Member member = service.memberSearch(memId, memPw);
		
		model.addAttribute("memId", member.getMemId());
		model.addAttribute("memPw", member.getMemPw());
		
		return "/memLoginOk";
		
	}
}
