package me.spring.ajaxse.beans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Component
public class UsersEntity {
	@NotEmpty
	@Size(min = 1, max = 20) // Collection => List<Character>
	@Length(min = 1, max = 20) // Array    => char[]
	private String userName;
	
	@NotNull
	@Digits(fraction = 0, integer = 3)
	@Range(min = 0, max = 200) 
	private Integer userAge;
	
	@NotEmpty
	@Size(min = 1, max = 2) // Collection => List<Character>
	@Length(min = 1, max = 2) // Array    => char[]
	@Value(value = "남자") 
	private String userGender;
	
	@NotEmpty
	@Size(min = 1, max = 20) // Collection => List<Character>
	@Length(min = 1, max = 20) // Array    => char[]
	@Email
	private String userEmail;

	public UsersEntity() {}
	public UsersEntity(String userName, Integer userAge, String userGender, String userEmail) {
		this.userName = userName;
		this.userAge = userAge;
		this.userGender = userGender;
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@Override
	public String toString() {
		return "UsersEntity [userName=" + userName + ", userAge=" + userAge + ", userGender=" + userGender
				+ ", userEmail=" + userEmail + "]";
	}
}
