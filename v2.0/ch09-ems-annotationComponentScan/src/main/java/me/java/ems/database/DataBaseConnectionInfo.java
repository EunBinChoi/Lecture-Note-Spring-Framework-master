package me.java.ems.database;

import org.springframework.core.env.Environment;

// ComponenetScan이 읽지 못하도록
//@Component // new DataBaseConnectionInfo()
public class DataBaseConnectionInfo {
	//@Value("${dbInfo.dev.driver}")
	private String driver;
	//@Value("${dbInfo.dev.url}")
	private String url;
	//@Value("${dbInfo.dev.userId}")
	private String userId;
	//@Value("${dbInfo.dev.userPw}")
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
	public void init(Environment env, String dbName) {
		if(dbName.equals("dev")) {
			this.driver = env.getProperty("dbInfo.dev.driver");
			this.url = env.getProperty("dbInfo.dev.url");
			this.userId = env.getProperty("dbInfo.dev.userId");
			this.userPw = env.getProperty("dbInfo.dev.userPw");
		}
		else {
			this.driver = env.getProperty("dbInfo.real.driver");
			this.url = env.getProperty("dbInfo.real.url");
			this.userId = env.getProperty("dbInfo.real.userId");
			this.userPw = env.getProperty("dbInfo.real.userPw");
		}
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
