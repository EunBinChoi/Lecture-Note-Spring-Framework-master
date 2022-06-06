package me.spring.file.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartRequest;

import me.spring.file.beans.FileDTO;
import me.spring.file.service.FileService;
import me.spring.file.util.MultipartFileUploadProcessor;
import me.spring.message.beans.MessageSourceVO;


@Controller
@RequestMapping("file")
public class FileController extends FileControllerHandler {
	private final Logger logger = LoggerFactory.getLogger(FileController.class);
	private final FileService fileService;
	private final MessageSource messageSource;
	
	@Autowired
	public FileController(FileService fileService, MessageSource messageSource) {
		this.fileService = fileService;
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
		messageSourceVO.getFile().setUpload   (messageSource.getMessage  ("file.upload", null, locale));
		return messageSourceVO;
	}
	////////////////////////////////////////////////////////////////////

	@RequestMapping("/upload")
	public String upload() {
		return "file/upload";
	}
	
	
	@RequestMapping(value = "/doUpload", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String doUpload(@Valid @ModelAttribute FileDTO file, MultipartRequest multipartRequest, Model model, HttpSession session) throws IllegalStateException, IOException, SecurityException, DataAccessException, SQLException {
		if (file == null) {
			session.setAttribute("upload", "upload-fail");
			return "redirect:/file/upload";
		}
		
		// 1) 실제 업로드된 파일을 하나의 리스트로 변경 
		// (하나의 input file tag에 여러개의 파일이 올라갈 수 있음 (multiple))
		// 2) 파일 이름 중복 처리 (RenamePolicy)
		MultipartFileUploadProcessor.parseFile(file, multipartRequest);		
		boolean isUpload = fileService.insert(file);
		System.out.println(file);
		System.out.println(isUpload);
		
		if(isUpload) {
			session.setAttribute("upload", "upload-success");
			model.addAttribute("file", file);
			return "redirect:/file/view";
		} else {
			session.setAttribute("upload", "upload-fail");
			return "redirect:/file/upload";
		}

		
	}
	

}
