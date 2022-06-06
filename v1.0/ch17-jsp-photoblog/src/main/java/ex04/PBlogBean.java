package ex04;

// 포토블로그 객체
public class PBlogBean {
	
	private int num; // 포토포스트의 유일성을 보장하는 번호 저장 (primary key)
	private String message; // 포토포스트의 메시지 저장 
	private String id; // 포토포스트 저장한 id 저장
	private String pdate; // 포토포스트 저장한 날짜와 시간 저장
	private String photo; // 포토포스트의 photo 명 저장
	private int hcnt; // 포토포스트의 좋아요 count 저장
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getHcnt() {
		return hcnt;
	}
	public void setHcnt(int hcnt) {
		this.hcnt = hcnt;
	}
}
