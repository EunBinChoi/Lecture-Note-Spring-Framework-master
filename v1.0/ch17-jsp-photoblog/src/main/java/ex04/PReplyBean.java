package ex04;

// �����α� ��� ��ü
public class PReplyBean {
	
	private int rnum; // ����� ���ϼ��� �����ϴ� ��ȣ (primary key)
	private int num; // ��������Ʈ�� num�� ���� (� ����Ʈ�� ������� ���еǴ� ��)
	private String id; // ����� �����ϴ� id ����
	private String rdate; // ����� ������ ��¥�� �ð��� ����
	private String comments; // ����� ������ ����
	
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
