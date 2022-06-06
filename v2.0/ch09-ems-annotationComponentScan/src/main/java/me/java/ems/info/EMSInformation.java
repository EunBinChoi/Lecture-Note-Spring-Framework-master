package me.java.ems.info;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.env.Environment;

import me.java.ems.database.DataBaseConnectionInfo;

// ComponenetScan이 읽지 못하도록
//@Component
public class EMSInformation {
	//@Value("${ems.info}")
	private String info;
	//@Value("${ems.copyRight}")
	private String copyRight;
	//@Value("${ems.ver}")
	private String ver;
	//@Value("${ems.sYear}")
	private int sYear;
	//@Value("${ems.sMonth}")
	private int sMonth;
	//@Value("${ems.sDay}")
	private int sDay;
	//@Value("${ems.eYear}")
	private int eYear;
	//@Value("${ems.eMonth}")
	private int eMonth;
	//@Value("${ems.eDay}")
	private int eDay;
	//@Value("#{'${ems.developers}'.split(',')}")
	private List<String> developers;
	private Map<String, String> administrators;
	private Map<String, DataBaseConnectionInfo> dbInfos;


	public EMSInformation() {
		super();
		System.out.println("EMSInformation()");
	}

	public EMSInformation(String info, String copyRight, String ver, int sYear, int sMonth, int sDay, int eYear,
			int eMonth, int eDay, List<String> developers, Map<String, String> administrators,
			Map<String, DataBaseConnectionInfo> dbInfos) {
		super();
		this.info = info;
		this.copyRight = copyRight;
		this.ver = ver;
		this.sYear = sYear;
		this.sMonth = sMonth;
		this.sDay = sDay;
		this.eYear = eYear;
		this.eMonth = eMonth;
		this.eDay = eDay;
		this.developers = developers;
		this.administrators = administrators;
		this.dbInfos = dbInfos;
		System.out.println("EMSInformation(String info, String copyRight, String ver, int sYear, int sMonth, int sDay, int eYear,\r\n"
				+ "			int eMonth, int eDay, List<String> developers, Map<String, String> administrators,\r\n"
				+ "			Map<String, DataBaseConnectionInfo> dbInfos)");
	}

	@SuppressWarnings("unchecked")
	public void init(Environment env) {
		String info = env.getProperty("ems.info");
		this.info = info;

		String ver = env.getProperty("ems.ver");
		this.ver = ver;

		String copyRight = env.getProperty("ems.copyRight");
		this.copyRight = copyRight;

		int sYear = Integer.parseInt(env.getProperty("ems.sYear"));
		this.sYear = sYear;

		int sMonth = Integer.parseInt(env.getProperty("ems.sMonth"));
		this.sMonth = sMonth;

		int sDay = Integer.parseInt(env.getProperty("ems.sDay"));
		this.sDay = sDay;

		int eYear = Integer.parseInt(env.getProperty("ems.eYear"));
		this.eYear = eYear;

		int eMonth = Integer.parseInt(env.getProperty("ems.eMonth"));
		this.eMonth = eMonth;

		int eDay = Integer.parseInt(env.getProperty("ems.eDay"));
		this.eDay = eDay;


		String developers = env.getProperty("ems.developers");

		List<String> developersParsed = List.of(developers.split(","));
		this.developers = developersParsed;

		// Map
		String administrators = env.getProperty("ems.administrators");
		JSONParser parser = new JSONParser();
		JSONObject administratorsParsed = null;
		try {
			administratorsParsed = (JSONObject)parser.parse(administrators);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.administrators = administratorsParsed;
	}
	public void outputEMSInfo() {
		System.out.print("\n\n");
		String devPeriod = sYear + "/" + sMonth + "/" + sDay + " ~ " + eYear + "/" + eMonth + "/" + eDay;
		System.out.println(info + "(" + devPeriod + ")" + "\n" + copyRight + "\n" + ver);
		System.out.println("Developers : " + developers);
		System.out.println("Administrator : " + administrators);
		outputDataBaseInfo();
		System.out.print("\n\n");
	}
	public void outputDataBaseInfo() {
		Set<String> keys = dbInfos.keySet();
		Iterator<String> iterator = keys.iterator();

		while (iterator.hasNext()) {
			String key = iterator.next();
			DataBaseConnectionInfo info = dbInfos.get(key);
			System.out.println("[" + key + "]");
			System.out.print("jdbcUrl:" + info.getUrl() + "\t");
			System.out.print("userId:" + info.getUserId() + "\t");
			System.out.print("userPw:" + info.getUserPw() + "\n");
		}
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCopyRight() {
		return copyRight;
	}

	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public int getsYear() {
		return sYear;
	}

	public void setsYear(int sYear) {
		this.sYear = sYear;
	}

	public int getsMonth() {
		return sMonth;
	}

	public void setsMonth(int sMonth) {
		this.sMonth = sMonth;
	}

	public int getsDay() {
		return sDay;
	}

	public void setsDay(int sDay) {
		this.sDay = sDay;
	}

	public int geteYear() {
		return eYear;
	}

	public void seteYear(int eYear) {
		this.eYear = eYear;
	}

	public int geteMonth() {
		return eMonth;
	}

	public void seteMonth(int eMonth) {
		this.eMonth = eMonth;
	}

	public int geteDay() {
		return eDay;
	}

	public void seteDay(int eDay) {
		this.eDay = eDay;
	}

	public List<String> getDevelopers() {
		return developers;
	}

	public void setDevelopers(List<String> developers) {
		this.developers = developers;
	}

	public Map<String, String> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(Map<String, String> administrators) {
		this.administrators = administrators;
	}

	public Map<String, DataBaseConnectionInfo> getDbInfos() {
		return dbInfos;
	}

	public void setDbInfos(Map<String, DataBaseConnectionInfo> dbInfos) {
		this.dbInfos = dbInfos;
	}
}
