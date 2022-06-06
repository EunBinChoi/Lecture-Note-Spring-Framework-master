package me.spring.member.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

public class MemberDTO {
	/*
	 * View 단에서 Controller에 넘겨주는 객체
	 */

	/*
	 * SIGNUP: id, curpwd, currepwd, name, email, phone
	 * UPDATE: id, curpwd, newpwd, newrepwd, name, email, phone 의 모든 값들을 담을 수 있음
	 */

	//@NotEmpty // update에서는 id가 안들어옴
	@Size(max = 20)
	private String mId;

	@NotEmpty
	@Size(max = 20) 
	private String mCurpwd;    // signup, update에서 사용

	//@NotEmpty // signup에서만 currepwd가 들어옴
	@Size(max = 20) 
	private String mCurrepwd;  // signup에서만 사용

	//@NotEmpty // update에서만 newpwd가 들어옴
	@Size(max = 20)
	private String mNewpwd;    // update에서만 사용

	//@NotEmpty // update에서만 newrepwd가 들어옴
	@Size(max = 20) 
	private String mNewrepwd;  // update에서만 사용

	//@NotEmpty // signup이나 update에서만 name가 들어옴
	@Size(max = 20) 
	private String mName;

	//@NotEmpty // signup이나 update에서만 email가 들어옴
	@Size(max = 20) // Collection => List<Character>
	@Pattern(regexp = "^[A-Za-z0-9\\-\\_\\.]+\\@[A-Za-z0-9\\-]+(\\.[A-Za-z]{2,3}){1,2}$")
	@Email
	private String mEmail;

	//@NotEmpty // signup이나 update에서만 phone가 들어옴
	@Size(max = 20)
	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
	private String mPhone;
	
	@Pattern(regexp = "^\\d{2}\\/\\d{2}\\/\\d{2}$")
	private String mUpdate;
	
	@Pattern(regexp = "^\\d{2}\\/\\d{2}\\/\\d{2}$")
	private String mRegdate;
	
	
	public MemberDTO() {}
	public MemberDTO(String mId, String mCurpwd, String mCurrepwd, String mNewpwd, String mNewrepwd, String mName,
			String mEmail, String mPhone, String mUpdate, String mRegdate) {
		this.mId = mId;
		this.mCurpwd = mCurpwd;
		this.mCurrepwd = mCurrepwd;
		this.mNewpwd = mNewpwd;
		this.mNewrepwd = mNewrepwd;
		this.mName = mName;
		this.mEmail = mEmail;
		this.mPhone = mPhone;
		this.mUpdate = mUpdate;
		this.mRegdate = mRegdate;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmCurpwd() {
		return mCurpwd;
	}
	public void setmCurpwd(String mCurpwd) {
		this.mCurpwd = mCurpwd;
	}
	public String getmCurrepwd() {
		return mCurrepwd;
	}
	public void setmCurrepwd(String mCurrepwd) {
		this.mCurrepwd = mCurrepwd;
	}
	public String getmNewpwd() {
		return mNewpwd;
	}
	public void setmNewpwd(String mNewpwd) {
		this.mNewpwd = mNewpwd;
	}
	public String getmNewrepwd() {
		return mNewrepwd;
	}
	public void setmNewrepwd(String mNewrepwd) {
		this.mNewrepwd = mNewrepwd;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	public String getmUpdate() {
		return mUpdate;
	}
	public void setmUpdate(String mUpdate) {
		this.mUpdate = mUpdate;
	}
	public String getmRegdate() {
		return mRegdate;
	}
	public void setmRegdate(String mRegdate) {
		this.mRegdate = mRegdate;
	}
	@Override
	public String toString() {
		return "MemberDTO [mId=" + mId + ", mCurpwd=" + mCurpwd + ", mCurrepwd=" + mCurrepwd + ", mNewpwd=" + mNewpwd
				+ ", mNewrepwd=" + mNewrepwd + ", mName=" + mName + ", mEmail=" + mEmail + ", mPhone=" + mPhone
				+ ", mUpdate=" + mUpdate + ", mRegdate=" + mRegdate + "]";
	}

	

}
