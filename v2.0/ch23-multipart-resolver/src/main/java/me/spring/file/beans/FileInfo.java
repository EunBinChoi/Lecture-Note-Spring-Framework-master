package me.spring.file.beans;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

public class FileInfo {
	private String originalFileName;
	private String storedFileName;

	private String fType;
	
	@Digits(integer = 10 * 1024 * 1024, fraction = 0)
	private Long fSize;
	
	public FileInfo() {}
	public FileInfo(String originalFileName, String storedFileName, String fType, Long fSize) {
		this.originalFileName = originalFileName;
		this.storedFileName = storedFileName;
		this.fType = fType;
		this.fSize = fSize;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getStoredFileName() {
		return storedFileName;
	}
	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}
	public String getfType() {
		return fType;
	}
	public void setfType(String fType) {
		this.fType = fType;
	}
	public Long getfSize() {
		return fSize;
	}
	public void setfSize(Long fSize) {
		this.fSize = fSize;
	}
	@Override
	public String toString() {
		return "FileInfo [originalFileName=" + originalFileName + ", storedFileName=" + storedFileName + ", fType=" + fType + ", fSize=" + fSize + "]";
	}

	
}
