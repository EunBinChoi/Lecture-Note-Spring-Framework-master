package me.spring.file.beans;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class FileEntity {
	
	@NotNull
	@Size(max = 10)
	private String  fno;
	
	@NotNull
	@Size(max = 20)
	private String  userName;
	
	@NotNull
	@Size(max = 30)
	private String  title;
	
	@NotNull
	@Size(max = 500)
	private String  content;
	
	@NotNull
	@Size(max = 3000)
	private String  oriFileNames;
	
	@NotNull
	@Size(max = 3000)
	private String  storedFileNames;
	
	@NotNull
	@Value("|")
	@Size(max = 1)
	private String  fSeperator;
	
	@NotNull
	@Value("0")
	@Size(max = 3)
	private String fCount;
	
	@Value("")
	@Size(max = 1000)
	private String  fType;
	
	@Value("0")
	@Size(max = 1000)
	private String  fSize;

	public FileEntity() {}

	public FileEntity(String fno, String userName, String title, String content, String oriFileNames,
			String storedFileNames, String fSeperator, String fCount, String fType, String fSize) {
		this.fno = fno;
		this.userName = userName;
		this.title = title;
		this.content = content;
		this.oriFileNames = oriFileNames;
		this.storedFileNames = storedFileNames;
		this.fSeperator = fSeperator;
		this.fCount = fCount;
		this.fType = fType;
		this.fSize = fSize;
	}

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOriFileNames() {
		return oriFileNames;
	}

	public void setOriFileNames(String oriFileNames) {
		this.oriFileNames = oriFileNames;
	}

	public String getStoredFileNames() {
		return storedFileNames;
	}

	public void setStoredFileNames(String storedFileNames) {
		this.storedFileNames = storedFileNames;
	}

	public String getfSeperator() {
		return fSeperator;
	}

	public void setfSeperator(String fSeperator) {
		this.fSeperator = fSeperator;
	}

	public String getfCount() {
		return fCount;
	}

	public void setfCount(String fCount) {
		this.fCount = fCount;
	}

	public String getfType() {
		return fType;
	}

	public void setfType(String fType) {
		this.fType = fType;
	}

	public String getfSize() {
		return fSize;
	}

	public void setfSize(String fSize) {
		this.fSize = fSize;
	}

	@Override
	public String toString() {
		return "FileEntity [fno=" + fno + ", userName=" + userName + ", title=" + title + ", content=" + content
				+ ", oriFileNames=" + oriFileNames + ", storedFileNames=" + storedFileNames + ", fSeperator="
				+ fSeperator + ", fCount=" + fCount + ", fType=" + fType + ", fSize=" + fSize + "]";
	}
	
	
	
	
	
}
