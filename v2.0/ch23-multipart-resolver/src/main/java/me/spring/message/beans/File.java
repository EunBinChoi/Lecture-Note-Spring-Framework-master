package me.spring.message.beans;

public class File {
	private String upload;

	public File() {}
	public File(String upload) {
		this.upload = upload;
	}

	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	
	@Override
	public String toString() {
		return "File [upload=" + upload + "]";
	}
	
	
}
