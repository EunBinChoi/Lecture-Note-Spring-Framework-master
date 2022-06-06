package me.spring.login.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class RegisterDTO {
	/*
	 * View 단에서 Controller에 넘겨주는 객체
	 */

	/*
	 * SIGNUP: id, curpwd, currepwd, name, email, phone
	 * UPDATE: id, curpwd, newpwd, newrepwd, name, email, phone 의 모든 값들을 담을 수 있음
	 */
	
	//@NotEmpty // update에서는 id가 안들어옴
	@Size(max = 20) // Collection => List<Character>
	@Length(max = 20) // Array    => char[]
	private String id;
	
	@NotEmpty
	@Size(max = 20) // Collection => List<Character>
	@Length(max = 20) // Array    => char[]
	private String curpwd;    // signup, update에서 사용
	
	//@NotEmpty // signup에서만 currepwd가 들어옴
	@Size(max = 20) // Collection => List<Character>
	@Length(max = 20) // Array    => char[]
	private String currepwd;  // signup에서만 사용
	
	//@NotEmpty // update에서만 newpwd가 들어옴
	@Size(max = 20) // Collection => List<Character>
	@Length(max = 20) // Array    => char[]
	private String newpwd;    // update에서만 사용
	
	//@NotEmpty // update에서만 newrepwd가 들어옴
	@Size(max = 20) // Collection => List<Character>
	@Length(max = 20) // Array    => char[]
	private String newrepwd;  // update에서만 사용
	
	//@NotEmpty // signup이나 update에서만 name가 들어옴
	@Size(max = 20) // Collection => List<Character>
	@Length(max = 20) // Array    => char[]
	private String name;
	
	//@NotEmpty // signup이나 update에서만 email가 들어옴
	@Size(max = 20) // Collection => List<Character>
	@Length(max = 20) // Array    => char[]
	@Pattern(regexp = "^[A-Za-z0-9\\-\\_\\.]+\\@[A-Za-z0-9\\-]+(\\.[A-Za-z]{2,3}){1,2}$")
	@Email
	private String email;
	
	//@NotEmpty // signup이나 update에서만 phone가 들어옴
	@Size(max = 20) // Collection => List<Character>
	@Length(max = 20) // Array    => char[]
	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
	private String phone;

	public RegisterDTO() {}
	public RegisterDTO(String id, String curpwd, String currepwd, String newpwd, String newrepwd, String name,
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
	public void setId(String id) {
		this.id = id;
	}
	public String getCurpwd() {
		return curpwd;
	}
	public void setCurpwd(String curpwd) {
		this.curpwd = curpwd;
	}
	public String getCurrepwd() {
		return currepwd;
	}
	public void setCurrepwd(String currepwd) {
		this.currepwd = currepwd;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getNewrepwd() {
		return newrepwd;
	}
	public void setNewrepwd(String newrepwd) {
		this.newrepwd = newrepwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "RegisterDTO [id=" + id + ", curpwd=" + curpwd + ", currepwd=" + currepwd + ", newpwd=" + newpwd
				+ ", newrepwd=" + newrepwd + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}

}
