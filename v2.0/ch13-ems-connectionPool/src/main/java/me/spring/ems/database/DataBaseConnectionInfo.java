package me.spring.ems.database;

public class DataBaseConnectionInfo {
	private String driver;
	private String url;
	private String userId;
	private String userPw;

	public DataBaseConnectionInfo() {
		super();
		System.out.println("DataBaseConnectionInfo()");
	}
	public DataBaseConnectionInfo(String driver, String url, String userId, String userPw) {
		super();
		this.driver = driver;
		this.url = url;
		this.userId = userId;
		this.userPw = userPw;
		System.out.println("DataBaseConnectionInfo(String driver, String url, String userId, String userPw)");
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
}
