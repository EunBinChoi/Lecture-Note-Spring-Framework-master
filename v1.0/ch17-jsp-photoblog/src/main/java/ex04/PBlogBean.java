package ex04;

// �����α� ��ü
public class PBlogBean {
	
	private int num; // ��������Ʈ�� ���ϼ��� �����ϴ� ��ȣ ���� (primary key)
	private String message; // ��������Ʈ�� �޽��� ���� 
	private String id; // ��������Ʈ ������ id ����
	private String pdate; // ��������Ʈ ������ ��¥�� �ð� ����
	private String photo; // ��������Ʈ�� photo �� ����
	private int hcnt; // ��������Ʈ�� ���ƿ� count ����
	
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
