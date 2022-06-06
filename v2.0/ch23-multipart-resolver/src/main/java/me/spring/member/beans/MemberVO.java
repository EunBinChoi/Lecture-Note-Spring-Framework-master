package me.spring.member.beans;

import java.util.Objects;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

public class MemberVO {
	/*
	 * View 단에 보여주는 객체 (보통 setter는 없고 getter만 존재) View 단에서는 주소값의 개념은 없고 같은 값을 가지면 같은
	 * 객체로 취급 => 이를 위해 hashCode(), equals() 재정의 registerDTO와 다른 점은 없지만 registerVO는
	 * read-only라는 특징을 가짐
	 */


	@Size(max = 20)
	private String mId;

	@Size(max = 20) 
	private String mCurpwd; // signup, update에서 사용

	@Size(max = 20) 
	private String mCurrepwd; // signup에서만 사용

	@Size(max = 20)
	private String mNewpwd; // update에서만 사용

	@Size(max = 20)
	private String mNewrepwd; // update에서만 사용

	@Size(max = 20) 
	private String mName;

	@Size(max = 20)
	@Pattern(regexp = "^[A-Za-z0-9\\-\\_\\.]+\\@[A-Za-z0-9\\-]+(\\.[A-Za-z]{2,3}){1,2}$")
	@Email
	private String mEmail;

	@Size(max = 20)
	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
	private String mPhone;

	@Pattern(regexp = "^\\d{2}\\/\\d{2}\\/\\d{2}$")
	private String mUpdate;
	
	
	@Pattern(regexp = "^\\d{2}\\/\\d{2}\\/\\d{2}$")
	private String mRegdate;

	
	
	
	public MemberVO() {}

	public MemberVO(String mId) {
		this.mId = mId;
	}

	public MemberVO(String mId, String mCurpwd) {
		this.mId = mId;
		this.mCurpwd = mCurpwd;
	}

	public MemberVO(String mId, String mCurpwd, String mCurrepwd, String mNewpwd, String mNewrepwd, String mName,
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
	public int hashCode() {
		return Objects.hash(mCurpwd, mCurrepwd, mEmail, mId, mName, mNewpwd, mNewrepwd, mPhone, mRegdate, mUpdate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		MemberVO other = (MemberVO) obj;
		return Objects.equals(mCurpwd, other.mCurpwd) && Objects.equals(mCurrepwd, other.mCurrepwd)
				&& Objects.equals(mEmail, other.mEmail) && Objects.equals(mId, other.mId)
				&& Objects.equals(mName, other.mName) && Objects.equals(mNewpwd, other.mNewpwd)
				&& Objects.equals(mNewrepwd, other.mNewrepwd) && Objects.equals(mPhone, other.mPhone)
				&& Objects.equals(mRegdate, other.mRegdate) && Objects.equals(mUpdate, other.mUpdate);
	}
	@Override
	public String toString() {
		return "MemberVO [mId=" + mId + ", mCurpwd=" + mCurpwd + ", mCurrepwd=" + mCurrepwd + ", mNewpwd=" + mNewpwd
				+ ", mNewrepwd=" + mNewrepwd + ", mName=" + mName + ", mEmail=" + mEmail + ", mPhone=" + mPhone
				+ ", mUpdate=" + mUpdate + ", mRegdate=" + mRegdate + "]";
	}

	
	


}