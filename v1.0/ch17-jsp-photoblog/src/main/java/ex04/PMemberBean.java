package ex04;

// ȸ�� ��ü
public class PMemberBean {
	
	private String id; // ȸ�� id ���� (primary key)
	private String pwd; // ȸ�� ��й�ȣ ����
	private String name; // ȸ�� �̸� ����
	private String profile; // ȸ�� ������ ���� ����
	
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
