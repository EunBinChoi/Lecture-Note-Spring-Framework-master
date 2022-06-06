package me.spring.message.beans;

public class Member {
	private String login;
	private String revise;
	private String delete;
	private String logout;

	public Member() {}
	public Member(String login, String revise, String delete, String logout) {
		this.login = login;
		this.revise = revise;
		this.delete = delete;
		this.logout = logout;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getRevise() {
		return revise;
	}
	public void setRevise(String revise) {
		this.revise = revise;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public String getLogout() {
		return logout;
	}
	public void setLogout(String logout) {
		this.logout = logout;
	}
	@Override
	public String toString() {
		return "Member [login=" + login + ", revise=" + revise + ", delete=" + delete + ", logout=" + logout + "]";
	}
	
}
