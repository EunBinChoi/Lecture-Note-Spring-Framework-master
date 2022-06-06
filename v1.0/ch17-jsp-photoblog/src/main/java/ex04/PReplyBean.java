package ex04;

// 포토블로그 댓글 객체
public class PReplyBean {
	
	private int rnum; // 댓글의 유일성을 보장하는 번호 (primary key)
	private int num; // 포토포스트의 num을 저장 (어떤 포스트의 댓글인지 구분되는 값)
	private String id; // 댓글을 저장하는 id 저장
	private String rdate; // 댓글을 저장한 날짜와 시간을 저장
	private String comments; // 댓글의 내용을 저장
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
