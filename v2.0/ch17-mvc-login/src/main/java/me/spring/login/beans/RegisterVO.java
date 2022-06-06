package me.spring.login.beans;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class RegisterVO {
	/*
	 * View 단에 보여주는 객체 (보통 setter는 없고 getter만 존재)
	 * View 단에서는 주소값의 개념은 없고 같은 값을 가지면 같은 객체로 취급
	 * => 이를 위해 hashCode(), equals() 재정의
	 * RegisterDTO와 다른 점은 없지만 RegisterVO는 read-only라는 특징을 가짐
	 * */
	private String id;
	private String curpwd;
	private String currepwd;
	private String newpwd;   // update에서만 사용
	private String newrepwd; // update에서만 사용
	private String name;
	private String email;
	private String phone;

	public RegisterVO() {}

	public RegisterVO(String id) {
		this.id = id;
	}
	
	public RegisterVO(String id, String curpwd) {
		this.id = id;
		this.curpwd = curpwd;
	}
	
	public RegisterVO(String id, String name, String email, String phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public RegisterVO(String id, String curpwd, String currepwd, String newpwd, String newrepwd, String name,
			String email, String phone) {
		this.id = id;
		this.curpwd = curpwd;
		this.currepwd = currepwd;
		this.newpwd = newpwd;
		this.newrepwd = newrepwd;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public String getCurpwd() {
		return curpwd;
	}

	public String getCurrepwd() {
		return currepwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public String getNewrepwd() {
		return newrepwd;
	}


	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}
	@Override
	public String toString() {
		return "RegisterVO [id=" + id + ", curpwd=" + curpwd + ", currepwd=" + currepwd + ", newpwd=" + newpwd
				+ ", newrepwd=" + newrepwd + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(curpwd, currepwd, email, id, name, newpwd, newrepwd, phone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if ((obj == null) || (getClass() != obj.getClass())) return false;
		RegisterVO other = (RegisterVO) obj;
		return Objects.equals(curpwd, other.curpwd) && Objects.equals(currepwd, other.currepwd)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(newpwd, other.newpwd)
				&& Objects.equals(newrepwd, other.newrepwd) && Objects.equals(phone, other.phone);
	}



}