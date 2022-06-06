package ex04;

// 회원 객체
public class PMemberBean {
	
	private String id; // 회원 id 저장 (primary key)
	private String pwd; // 회원 비밀번호 저장
	private String name; // 회원 이름 저장
	private String profile; // 회원 프로필 포토 저장
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
}
