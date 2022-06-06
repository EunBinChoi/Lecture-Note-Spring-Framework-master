package me.spring.file.beans;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

public class FileVO {
	
	@Size(max = 10)
	private String  fno;

	@Size(max = 20)
	private String userName;
	
	@Size(max = 30)
	private String title;
	
	@Size(max = 50)
	private String content;
	
	
	@Value("|")
	private String fileSeperator;
	
	@Value("0")
	@Digits(integer = 100, fraction = 0)
	private Integer fileRealCount; // multiple 파일까지 고려한 총 개수
	
	@Size(max = 100)
	private List<FileInfo> fileInfos = new Vector<>();

	
	
	public FileVO() {}

	public FileVO(String fno, String userName, String title, String content, String fileSeperator,
			Integer fileRealCount, List<FileInfo> fileInfos) {
		this.fno = fno;
		this.userName = userName;
		this.title = title;
		this.content = content;
		this.fileSeperator = fileSeperator;
		this.fileRealCount = fileRealCount;
		this.fileInfos = fileInfos;
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

	public String getFileSeperator() {
		return fileSeperator;
	}


	public void setFileSeperator(String fileSeperator) {
		this.fileSeperator = fileSeperator;
	}

	public Integer getFileRealCount() {
		return fileRealCount;
	}

	public void setFileRealCount(Integer fileRealCount) {
		this.fileRealCount = fileRealCount;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}


	@Override
	public int hashCode() {
		return Objects.hash(content, fileInfos, fileRealCount, fileSeperator, fno, title, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		FileVO other = (FileVO) obj;
		return Objects.equals(content, other.content) && Objects.equals(fileInfos, other.fileInfos)
				&& Objects.equals(fileRealCount, other.fileRealCount)
				&& Objects.equals(fileSeperator, other.fileSeperator) && Objects.equals(fno, other.fno)
				&& Objects.equals(title, other.title) && Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "FileVO [fno=" + fno + ", userName=" + userName + ", title=" + title + ", content=" + content
				+ ", fileSeperator=" + fileSeperator + ", fileRealCount=" + fileRealCount + ", fileInfos=" + fileInfos
				+ "]";
	}


}
