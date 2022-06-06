package me.spring.member.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.spring.member.beans.MemberDTO;
import me.spring.member.beans.MemberVO;
import me.spring.member.service.MemberService;
import me.spring.message.beans.MessageSourceVO;


@Controller
@RequestMapping("member")
public class MemberController extends MemberControllerHandler {
	private final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private final MemberService memberService;
	private final MessageSource messageSource;
	
	
	@Autowired
	public MemberController(MemberService memberService, MessageSource messageSource) {
		this.memberService = memberService;
		this.messageSource = messageSource;
	}

	@ModelAttribute("cp")
	public String contextPath(HttpServletRequest request) {
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
		messageSourceVO.getFile().setUpload   (messageSource.getMessage("file.upload", null, locale));
		return messageSourceVO;
	}
	//////////////////////////////////////////////////////////////////////////

	
	//////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		return "member/login";
	}

	/*
	 * login에 실패하는 경우
	 * 1) 입력된 객체가 null인 경우
	 * 2) 입력된 id나 pwd가 null인 경우 (프론트 쪽에서 처리해줬지만 한번 더 처리 (코드의 유지보수를 위해))
	 * 3) 입력된 id나 pwd가 DB에 저장된 값과 맞지 않는 경우
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(@Valid @ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) throws SQLException, DataAccessException {
		System.out.println(memberDTO);

		// 1)
		// 2)
		if (memberDTO == null || memberDTO.getmId() == null || memberDTO.getmCurpwd() == null) {
			model.addAttribute("loginFailId",  memberDTO.getmId());
			model.addAttribute("loginFailPwd", memberDTO.getmCurpwd());
			session.setAttribute("login", "login-fail");
			return "member/login"; // redirect 하면 데이터가 날라감 (loginFailId, loginFailPwd)
		}

		// Database에서 검색
		MemberDTO member = memberService.select(memberDTO.getmId());

		// 3)
		if (member == null || !memberDTO.getmId().equals(member.getmId())
				|| !memberDTO.getmCurpwd().equals(member.getmCurpwd())) {

			// 로그인 시도시 작성했던 id, pwd 저장
			// 이후 signup 페이지에서 보여주기 위함
			//session.setAttribute("id",  memberDTO.getId());
			//session.setAttribute("pwd", memberDTO.getCurpwd());
			model.addAttribute("loginFailId",  memberDTO.getmId());
			model.addAttribute("loginFailPwd", memberDTO.getmCurpwd());
			session.setAttribute("login", "login-fail");
			return "member/login"; // redirect 하면 데이터가 날라감 (loginFailId, loginFailPwd)
		}

		if (member != null) {
			session.setAttribute("idKey", member.getmId());
			session.setAttribute("login", "login-success");
			return "redirect:/";
		} else {
			model.addAttribute("loginFailId",  memberDTO.getmId());
			model.addAttribute("loginFailPwd", memberDTO.getmCurpwd());
			session.setAttribute("login", "login-fail");
			return "member/login"; // redirect 하면 데이터가 날라감 (loginFailId, loginFailPwd)
		}
	}

	//////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(HttpServletRequest request) {
		return "member/signup";
	}
	
	// registerDTO의 @Valid 체크를 굳이 하지 않음
	// 만약 로그인 실패시 입력한 ID/PWD가 없어도 그냥 수행하겠다!
	// login이 fail되고 입력한 ID/PWD를 가지고 signup 할 때
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(@ModelAttribute MemberDTO member, Model model) {
		if(member != null && member.getmId() != null && member.getmCurpwd() != null
				&& !member.getmId().equals("") && !member.getmCurpwd().equals("")) {
			
			// 이전에 입력했던 ID/PWD를 통해 signup하기 위해서 기존에 입력했던 값을 뷰단에 보여줌
			model.addAttribute("member", new MemberVO(member.getmId(), member.getmCurpwd()));
		}
		return "member/signup";
	}

	/*
	 * signup에 실패하는 경우
	 * 1) 입력한 객체가 null인 경우
	 * 2) 입력된 id, curpwd, currepwd null인 경우 (프론트 쪽에서 처리해줬지만 한번 더 처리 (코드의 유지보수를 위해))
	 * 3) 입력된 curpwd와 currepwd가 다른 경우
	 * 4) 입력된 id가 이미 DB에 저장되어있는 경우
	 *
	 */
	@RequestMapping(value = "/doSignup", method = RequestMethod.POST)
	public String doSignup(@Valid @ModelAttribute MemberDTO memberDTO, HttpSession session) 
			throws SQLException, DataAccessException {
		System.out.println(memberDTO);

		// 1)
		// 2)
		if ((memberDTO == null) || memberDTO.getmId() == null 
				|| memberDTO.getmCurpwd() == null || memberDTO.getmCurrepwd() == null) {
			session.setAttribute("signup", "signup-fail");
			return "redirect:/member/signup";
		}

		// 3)
		if (!memberDTO.getmCurpwd().equals(memberDTO.getmCurrepwd())) {
			session.setAttribute("signup", "signup-fail");
			return "redirect:/member/signup";
		}
		
		boolean isSignup = memberService.signup(memberDTO);

		if (isSignup) {
			session.setAttribute("signup", "signup-success");
			return "redirect:/member/login";
		} else { // 4)
			session.setAttribute("signup", "signup-fail");
			return "redirect:/member/signup";
		}
	}

	//////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, HttpSession session) {
		/*
		 * 로그인 정보가 세션에 저장되어있는지 확인 (idKey 값에 저장)
		 * 해당 페이지는 로그인하지 않으면 접근 불가능하기 때문
		 * (이후에 interceptor로 처리 가능)
		 */
		if (session.getAttribute("idKey") == null) {
			return "redirect:/member/login";
		}

		MemberVO member = new MemberVO((String) session.getAttribute("idKey"));
		model.addAttribute("member", member);

		return "member/update";
	}

	/*
	 * update에 실패하는 경우
	 * 1) 세션에 idKey 값이 없는 경우
	 * 2) 입력한 객체가 null인 경우
	 * 3) 입력된 id, curpwd, newpwd, newrepwd null인 경우 (프론트 쪽에서 처리해줬지만 한번 더 처리 (코드의 유지보수를 위해))
	 * 4) 입력된 newpwd와 newrepwd가 다른 경우 (프론트 쪽에서 처리해줬지만 한번 더 처리 (코드의 유지보수를 위해))
	 * 5) 입력된 id가 DB에 저장되어있지 않은 경우 (입력된 id를 통해 select이 불가능할 경우)
	 * 6) 입력된 id의 curpwd와 DB에서 select한 curpwd가 다를 경우
	 *
	 */
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(@Valid @ModelAttribute MemberDTO memberDTO, HttpSession session) 
			throws SQLException, DataAccessException {
		System.out.println(memberDTO);

		// 1)
		// 수정할 때는 id를 입력하지 않고 세션에 있는 값을 사용하므로 세션 값을 이용해서 registerDTO 완성
		if (session.getAttribute("idKey") != null) {
			memberDTO.setmId((String) session.getAttribute("idKey"));
		} else {
			session.setAttribute("login", "session-end");
			return "redirect:/member/login";
		}

		// 2)
		// 3)
		if (memberDTO == null || memberDTO.getmId() == null || memberDTO.getmCurpwd() == null
				|| memberDTO.getmNewpwd() == null || memberDTO.getmNewrepwd() == null) {
			session.setAttribute("update", "update-fail");
			return "redirect:/member/update";
		}

		// 4)
		if (!memberDTO.getmNewpwd().equals(memberDTO.getmNewrepwd())) {
			session.setAttribute("update", "update-fail");
			return "redirect:/member/update";
		}

		boolean isUpdate = memberService.update(memberDTO);
		if (isUpdate) {
			session.setAttribute("update", "update-success");
			return "redirect:/";
		} else { // 5) 6)
			session.setAttribute("update", "update-fail");
			return "redirect:/member/update";
		}
	}

	//////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpSession session) {
		/*
		 * 로그인 정보가 세션에 저장되어있는지 확인 (idKey 값에 저장)
		 * 해당 페이지는 로그인하지 않으면 접근 불가능하기 때문
		 * (이후에 interceptor로 처리 가능)
		 */
		if (session.getAttribute("idKey") == null) {
			return "redirect:/member/login";
		}

		return "member/delete";
	}

	/*
	 * delete에 실패하는 경우
	 * 1) 세션에 idKey 값이 없는 경우
	 * 2) 입력된 idKey를 통해 id를 검색할 수 없는 경우
	 */
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	public String doDelete(HttpSession session) throws SQLException, DataAccessException {

		// 1)
		// 삭제도 id를 입력하지 않고 세션에 있는 값을 사용
		String id = null;
		if (session.getAttribute("idKey") != null) {
			id = (String) session.getAttribute("idKey");
		} else {
			session.setAttribute("login", "session-end");
			return "redirect:/member/login";
		}

		boolean isDelete = false;
		if (id != null) {
			isDelete = memberService.delete(id);
		}

		if (isDelete) {
			if (session.getAttribute("idKey") != null) {
				session.removeAttribute("idKey");
			}
			if (session.getAttribute("login") != null) {
				session.removeAttribute("login");
			}
			if (session.getAttribute("signup") != null) {
				session.removeAttribute("signup");
			}
			if (session.getAttribute("update") != null) {
				session.removeAttribute("update");
			}
			if (session.getAttribute("delete") != null) {
				session.removeAttribute("delete");
			}
			session.setAttribute("delete", "delete-success");
			return "redirect:/member/login";
		} else { // 2)
			session.setAttribute("delete", "delete-fail");
			return "redirect:/member/delete";
		}

	}

	/*
	 * logout에 실패하는 경우
	 */
	@RequestMapping(value = "/doLogout", method = RequestMethod.GET)
	public String doLogout(HttpSession session) {
		if (session.getAttribute("idKey") != null) {
			session.removeAttribute("idKey");
		}
		if (session.getAttribute("login") != null) {
			session.removeAttribute("login");
		}
		if (session.getAttribute("signup") != null) {
			session.removeAttribute("signup");
		}
		if (session.getAttribute("update") != null) {
			session.removeAttribute("update");
		}
		if (session.getAttribute("delete") != null) {
			session.removeAttribute("delete");
		}
		session.setAttribute("login", "logout");
		return "redirect:/member/login";
	}

	
	///////////////////////////////////////////////////////////////////////////////
	////////////////////////////// 비동기 통신 함수 ///////////////////////////////////
	@RequestMapping(value = "/checkDuplicateId", 
			method = RequestMethod.GET,
			consumes = "application/x-www-form-urlencoded; charset=utf8",
			produces = "text/html; charset=utf8")
	@ResponseBody
	public String checkDuplicateId(@RequestParam String id) throws SQLException, DataAccessException {	
		System.out.println(id);
		if(id == null || id.equals("")) return "blank";
		
		boolean result = memberService.canSelect(id);
		return result ? "duplicate" : "non-duplicate";
	}
	
	@RequestMapping(value = "/checkIdPwd", 
			method = RequestMethod.POST,
			consumes = "application/json; charset=utf8",
			produces = "text/html; charset=utf8")
	@ResponseBody
	public String checkIdPwd(@Valid @RequestBody MemberDTO member) throws SQLException, DataAccessException { 
		// @RequestBody + MessageConverter (JSON를 Convert) 빈 설정 (RequestMappingHandlerAdapter requestHandler)
		System.out.println(member);
		if(member.getmId() == null || member.getmId().equals("") ) return "blank";
		if(member.getmCurpwd() == null || member.getmCurpwd().equals("")) return "blank";
		
		boolean result = memberService.canUpdate(member);
		return result ? "present" : "non-present";
	}

}
