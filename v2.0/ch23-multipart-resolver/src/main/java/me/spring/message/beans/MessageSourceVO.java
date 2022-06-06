package me.spring.message.beans;

public class MessageSourceVO {
	private  Common common;
	private  Member member;
	private  File file;
	
	
	public MessageSourceVO() {}


	public Common getCommon() {
		if(common == null) {
			common = new Common();
		}
		return common;
	}

	public Member getMember() {
		if(member == null) {
			member = new Member();
		}
		return member;
	}

	public File getFile() {
		if(file == null) {
			file = new File();
		}
		return file;
	}

	@Override
	public String toString() {
		return "MessageSourceVO [common=" + common + ", member=" + member + ", file=" + file + "]";
	}
	
}
