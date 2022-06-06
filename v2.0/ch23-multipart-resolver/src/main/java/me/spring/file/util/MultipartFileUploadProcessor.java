package me.spring.file.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import me.spring.file.beans.FileDTO;
import me.spring.file.beans.FileInfo;

/*
 * form이 전송될 때 일반적인 parameter와 파일 내용물을 담는 parameter를 구분하기 위한 클래스
 * */
public class MultipartFileUploadProcessor {
	private static final String UPLOAD_FOLDER 
	= "D:\\GoottAcademy-2\\spring-workspace\\ch23-multipart-resolver\\src\\main\\webapp\\resources\\upload";
		
	public static void parseFile(FileDTO file, MultipartRequest multipartRequest) throws IllegalStateException, IOException, SecurityException {
		// 파일 내용 전송
		
		file.setFileSeperator("|"); // file seperator 값 추가
		
		List<FileInfo> fileInfoList = file.getFileInfos();
		Iterator<?> files = multipartRequest.getFileNames();
		
		while (files.hasNext()) { // request 자체의 파일 개수
			String name = (String) files.next(); 
			List<MultipartFile> multiFiles = multipartRequest.getFiles(name);

			for (MultipartFile multiFile : multiFiles) { // 하나의 request 안에 포함된 파일들 스캔
				String originalFileName = multiFile.getOriginalFilename();
				File originalFile = new File(UPLOAD_FOLDER + File.separator + originalFileName);

				
				Long fileSize = multiFile.getSize();
				String fileContentType = multiFile.getContentType();

				FileInfo fileInfo = new FileInfo();
				fileInfo.setOriginalFileName(originalFile.getName());
				fileInfo.setfType(fileContentType);
				fileInfo.setfSize(fileSize);
				
				
				if(fileSize!= 0) {
					if (!originalFile.exists()) {
						fileInfo.setStoredFileName(originalFile.getName());
						multiFile.transferTo(originalFile); // 서버에 저장
					}
					else {
						DefaultFileRenamePolicy renamePolicy = new DefaultFileRenamePolicy();
						File renameFile = renamePolicy.rename(originalFile);
						fileInfo.setStoredFileName(renameFile.getName());
						multiFile.transferTo(renameFile); // 서버에 저장
					}
				}
				fileInfoList.add(fileInfo);
			}
		}
		
		file.setFileRealCount(fileInfoList.size());
	}


}
