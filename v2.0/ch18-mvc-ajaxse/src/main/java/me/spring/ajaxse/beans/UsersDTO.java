package me.spring.ajaxse.beans;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


// Annotation 정리:
// https://gmlwjd9405.github.io/2018/12/02/spring-annotation-types.html
// https://hyeran-story.tistory.com/81
// https://bepoz-study-diary.tistory.com/242
@Component
public class UsersDTO {
	// @NotNull 
	// "", " "은 허용, null만 불허
	// String, int (기본값 0), long, Integer (기본값 null)
	// CharSequence, Collection, Map, Array에 사용 가능
	
	// @NotBlank 
	// "", " ", null 모두 불허
	// character's sequence의 trim 처리를 한 결과 값이 not empty 인지를 보기 때문
	// String에 사용 가능 (int 불가)
	
	// @NotEmpty 
	// " "은 허용, null, "" 불허 
	// String, CharSequence, Collection, Map, Array 사용 가능 (int 불가)
	// size/length 체크
	
	@NotEmpty
	@Size(min = 1, max = 20) // Collection => List<Character>
	@Length(min = 1, max = 20) // Array    => char[]
	private String userName;
	
	@NotNull
	@Digits(fraction = 0, integer = 3, message = "@Digits (0 ~ 3)")
	@Range(min = 0, max = 200, message = "@Range (0 ~ 200)") 
	private Integer userAge; // 
	
	@NotEmpty
	@Size(min = 1, max = 2) // Collection => List<Character>
	@Length(min = 1, max = 2) // Array    => char[]
	@Value(value = "남자") // 라디오 버튼 기본값
	private String userGender;
	
	@NotEmpty
	@Size(min = 1, max = 20) // Collection => List<Character>
	@Length(min = 1, max = 20) // Array    => char[]
	@Pattern(regexp = "^[A-Za-z0-9\\-\\_\\.]+\\@[A-Za-z0-9\\-]+(\\.[A-Za-z]{2,3}){1,2}$")
	@Email
	private String userEmail;

	public UsersDTO() {}
	public UsersDTO(String userName, Integer userAge, String userGender, String userEmail) {
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
