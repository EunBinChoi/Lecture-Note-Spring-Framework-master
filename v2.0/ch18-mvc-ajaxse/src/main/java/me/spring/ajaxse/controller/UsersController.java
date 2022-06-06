package me.spring.ajaxse.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import me.spring.ajaxse.beans.UsersDTO;
import me.spring.ajaxse.service.UsersService;

@Controller
public class UsersController extends UserControllerExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(UsersController.class);
	private final UsersService usersService;

	@Autowired
	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	// model.addAttribute("cp", request.getContextPath());
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}


	/* RequestMapping
	 *
	 * - search
	 * - register
	 * : 다른 jsp로 forward 되는 게 아니라 현재 view page에서 내용물을 포함
	 * */
	@RequestMapping(value = "/search", method = RequestMethod.GET,
					//consumes = "application/x-www-form-urlencoded; charset=utf8", // 기본값
					produces = "application/json; charset=utf8") // json stringify
	@ResponseBody // 해당 함수의 반환값을 현재 view page에 포함시키겠다!
	public String search(@RequestParam(required = false, defaultValue = "") String userName) throws DataAccessException, SQLException {
		// @RequestParam: 파라미터로 전달한 값을 userName에 넣음
		
		System.out.println(userName);
		return usersService.select(userName);
	}


	/*
	 * ====================================================================================================
	 * @RequestParam: 1개의 http 요청 파라미터를 받기 위해 사용
	 * => 생성자를 통해 1:1 바인딩
	 * => 보통 파라미터의 변수 이름과 매개변수 이름을 같게 설정
	 * => 만약 파라미터의 변수 이름 (username)과 매개변수 이름 (userName)이 다를 경우에는 (@RequestParam(value = "username") String userName)으로 작성하면 됨
	 * => 요청에 매개변수가 필수적임 (@RequestParam(required = true))가 기본 설정 => 만약 파라미터를 전달하지 않으면 400 Eror 반환
	 * => @RequestParam(required = false)이면 파라미터가 없어도 되고 매개변수가 null로 바인딩
	 * => @RequestParam(required = false, defaultValue ="0" String userName)으로 디폴트 값 설정 가능
	 * (application/x-www-form-urlencoded)
	 * 
	 * @RequestParam 객체를 해결하는 순서
	 * 1) 어떤 클래스가 생성자가 1개의 인자를 받는데, 해당 인자가 @RequestParam 으로 바인딩 해오는 값과 일치한다면 해당 클래스의 인스턴스 주입
	 * 
	 * 데이터 바인딩
	 * 1) @RequestParam은 쿼리 파라미터만 바인딩
	 * ====================================================================================================
	 * @ModelAttribute: multipart/form-data 형태의 HTTP Body 내용과 HTTP 파라미터 데이터 (query string, form 형식)를 setter를 통해 Java 객체에 매핑
	 * => 객체의 필드를 바인딩할 수 있는 생성자 혹은 setter 필요
	 * => setter를 통해 바인딩, usersDTO.setUsersName(model.getAttribute("usersName"))
	 * => query string, form 형식이 아닌 데이터는 처리할 수 없음 (json 처리 불가)
	 * => @Valid 오류 날 때 BindException
	 * 
	 * @ModelAttribute 객체를 해결하는 순서
	 * 1) Model Attribute 가 있다면 model을 통해 
	 * 2) @SessionAttribute 에 값이 있다면 Http Session 을 통해
	 * 3) 기본 생성자를 통해서
	 * 4) 쿼리 파라미터 (Query Parameter)나 form 필드와 일치하는 인자를 받는 생성자를 통해서
	 * 
	 * 데이터 바인딩
	 * 1) @ModelAttribute 후에 WebExchangeDataBinder가 쿼리 파라미터와 form필드 이름을 바인딩할 Object 필드명과 비교 => 실패하면 BindException
	 * (Query Parameter, form Data, multiparts 모두 바인딩)
	 * ====================================================================================================
	 *
	 * @RequestBody: 클라이언트가 전송하는 json (application/json) 형태의 HTTP Body 내용을 Java Object으로 변환해줌
	 * => 변환하기 위해서 requestMappingHandlerAdapter (HttpMessageConverter 설정 필요)을 빈으로 설정
	 * => request를 body에 전달하지 않는 get 방식에서는 에러 발생
	 * => @RequestBody를 사용할 객체는 필드를 바인딩할 생성자나 setter가 필요 없음 (JacksonHttpMessageConverter가 핸들링)
	 * => @Valid 오류 날 때 MethodArgumentNotValidException
	 * 
	 * @RequestBody 객체를 해결하는 순서
	 * 1) HttpMessageReader가 request body를 Java Object로 역직렬화
	 * ====================================================================================================
	 * */
	
	

	/* @RequestMapping
	 * value, method, produces, consumes
	 * consumes: 수신할 데이터 포맷 (클라이언트 (index.jsp, index.js)가 서버 (UsersController.java)에게 보내는 데이터 타입)
	 * produces: 출력할 데이터 포맷 (서버 (UsersController.java)가 클라이언트 (index.jsp, index.js)에게 반환하는 데이터 타입)
	 *
	 * */
	
	/*
	 * @Valid vs @Validated
	 * @Valid: controller에서만 동작 (request handler mapping시 동작) (java 제공)
	 *  => throw MethodArgumentNotValidException
	 * @Validated: controller가 아닌 다른 계층에서도 동작 가능  / validate할 특정 그룹 지정 가능 (spring 제공)
	 * 	=> throw ConstraintViolationException
	 * 
	 * https://bepoz-study-diary.tistory.com/413 (**)
	 * https://goodgid.github.io/Spring-MVC-Valid-And-Validated/
	 * */
	@RequestMapping(value = "/register", method = RequestMethod.POST,
					consumes = "application/json; charset=utf8",
					produces = "text/html; charset=utf8") // "register-success / register-fail"
	@ResponseBody // 해당 함수의 반환값을 현재 view page에 포함시키겠다!
	public String register(@Valid @RequestBody UsersDTO usersDTO) throws DataAccessException, SQLException  {		
		System.out.println(usersDTO);
		int result = usersService.insert(usersDTO);
		System.out.println(result);
		if(result > 0) return "register-success";
		return "register-fail";
	}

}
