package me.spring.member.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Value;

public class MemberEntity {
	/*
	 * 실제 DB 테이블과 1:1 매핑되는 클래스
	 * */

	@NotNull
	@Size(max = 20) 
	private String mId;

	@NotNull
	@Size(max = 20)
	private String mPwd;

	
	@Size(max = 20)
	@Value("")
	private String mName;

	
	@Size(max = 20)
	@Value("")
	@Pattern(regexp = "^[A-Za-z0-9\\-\\_\\.]+\\@[A-Za-z0-9\\-]+(\\.[A-Za-z]{2,3}){1,2}$")
	@Email
	private String mEmail;

	@Size(max = 20)
	@Value("")
	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
	private String mPhone;
	
	@Value("")
	@Pattern(regexp = "^\\d{2}\\/\\d{2}\\/\\d{2}$")
	private String mUpdate;
	
	@Value("")
	@Pattern(regexp = "^\\d{2}\\/\\d{2}\\/\\d{2}$")
	private String mRegdate;

	
	public MemberEntity() {}	
	public MemberEntity(String mId, String mPwd, String mName, String mEmail, String mPhone, String mUpdate,
			String mRegdate) {
		this.mId = mId;
		this.mPwd = mPwd;
		this.mName = mName;
		this.mEmail = mEmail;
		this.mPhone = mPhone;
		this.mUpdate = mUpdate;
		this.mRegdate = mRegdate;
	}
	
	public MemberEntity(String mId, String mPwd, String mName, String mEmail, String mPhone) {
		this.mId = mId;
		this.mPwd = mPwd;
		this.mName = mName;
		this.mEmail = mEmail;
		this.mPhone = mPhone;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPwd() {
		return mPwd;
	}

	public void setmPwd(String mPwd) {
		this.mPwd = mPwd;
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
		return "MemberEntity [mId=" + mId + ", mPwd=" + mPwd + ", mName=" + mName + ", mEmail=" + mEmail + ", mPhone="
				+ mPhone + ", mUpdate=" + mUpdate + ", mRegdate=" + mRegdate + "]";
	}

	
	
	
}
