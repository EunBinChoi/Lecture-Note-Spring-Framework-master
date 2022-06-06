package controller.com.tistory.gangzzang;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import model.com.tistory.gangzzang.FileDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {

	@RequestMapping(value = "/file.do", method = RequestMethod.GET)
	public ModelAndView fileForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fileForm");
		return mv;
	}
	
	@RequestMapping(value = "/file.do", method = RequestMethod.POST)
	public String fileSubmit(FileDTO dto) {
		MultipartFile file = dto.getFile();
		if (file != null) {
			String fileName = file.getOriginalFilename();
			dto.setFileName(fileName);
			try {
				/* 
				- - FileOutputStream 방법을 사용 - -
				byte[] fileData = file.getBytes();
				FileOutputStream output = new FileOutputStream("/" + fileName);
				output.write(fileData);
				*/
				
				/*
				 * - - File 방법 사용 - -
				 */
				File file2 = new File("C:/images/" + fileName);
				file.transferTo(file2);
			} catch (IOException e) {
				e.printStackTrace();
			} // try - catch
		} // if
		/* 데이터 베이스 처리를 현재 위치에서 처리*/
		return "redirect:getBoardList.do";
	}
	
}
