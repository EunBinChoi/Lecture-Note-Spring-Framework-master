package me.java.ems.main;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import me.java.ems.beans.Student;
import me.java.ems.config.AppConfig;
import me.java.ems.info.EMSInformation;
import me.java.ems.service.StudentDeleteService;
import me.java.ems.service.StudentModifyService;
import me.java.ems.service.StudentRegisterService;
import me.java.ems.service.StudentSelectAllService;
import me.java.ems.service.StudentSelectService;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		// DB에 저장할 데이터를 배열로 임시 선언
		String[] sNums = { "H39r8djakndfae32", "H39asdfaelu42o23", "H39iiemamca8w9h4", "H39lkmn754fghia7",
				"H39plo865cuy8k92", "H39mnbviiaed89q1", "H399omjjyv56t3d5", "H39lczaqwg644gj8", "H39ymbcsh74thgh2",
				"H39lesvj7544vf89" };

		String[] sIds = { "rabbit", "hippo", "raccoon", "elephant", "lion", "tiger", "pig", "horse", "bird", "deer" };

		String[] sPws = { "96539", "94875", "15284", "48765", "28661", "60915", "30028", "29801", "28645", "28465" };

		String[] sNames = { "agatha", "barbara", "chris", "doris", "elva", "fiona", "holly", "jasmin", "lena",
				"melissa" };

		int[] sAges = { 19, 22, 20, 27, 19, 21, 19, 25, 22, 24 };
		String[] sGenders = { "M", "W", "W", "M", "M", "M", "W", "M", "W", "W" };
		String[] sMajors = { "English Literature", "Korean Language and Literature", "French Language and Literature",
				"Philosophy", "History", "Law", "Statistics", "Computer", "Economics", "Public Administration" };

		// 직접 객체 주입
		// StudentContainer container = new StudentContainer();

		//GenericXmlApplicationContext ctx
		//	= new GenericXmlApplicationContext("applicationContext.xml");
		AnnotationConfigApplicationContext ctx
		= new AnnotationConfigApplicationContext(AppConfig.class);

		////////////////////////////////////////////////////
		// .properties 파일 읽기
		// Environment: 프로퍼티 통합관리
		Environment env = ctx.getEnvironment();

		////////////////////////////////////////////////////

		//DataBaseConnectionInfo dataBaseConnectionInfo
		//= ctx.getBean("dataBaseConnectionInfo",
		//		me.java.ems.database.DataBaseConnectionInfo.class);
		//dataBaseConnectionInfo.init(env, "dev");


		//EMSInformation emsInformation = ctx.getBean("EMSInformation", me.java.ems.info.EMSInformation.class);
		//emsInformation.init(env);
		//emsInformation.setDbInfos(Map.of("dev", dataBaseConnectionInfo));
		//System.out.println(emsInformation.getDevelopers().get(0));



		EMSInformation emsInformation
		= ctx.getBean("EMSInformation", me.java.ems.info.EMSInformation.class);
		emsInformation.outputEMSInfo();


		for (int i = 0; i < sNums.length; i++) {
			register(ctx, sNums[i], sIds[i], sPws[i], sNames[i], sAges[i], sGenders[i], sMajors[i]);
		}
		selectAll(ctx);


		System.out.println("\n================================================ Small Test ==================="
				+ "==============================================================================");
		System.out.println("Student \"H39lesvj7544vf89\" info before modified");
		Student stu = select(ctx, "H39lesvj7544vf89");
		stu.setsPw("00000");
		stu.setsAge(24);
		stu.setsMajor("Vocal Music");
		modify(ctx, stu);
		System.out.println("Student \"H39lesvj7544vf89\" info after modified");
		select(ctx, "H39lesvj7544vf89");
		System.out.println("\n=============================================================================="
				+ "==============================================================================");
		System.out.println("\n\n");

		selectAll(ctx);


		while (true) {
			String str = "";

			System.out.println("\n=============================================================================="
					+ "==============================================================================");
			System.out.println("Select number.");
			System.out.println("1. Check student information");
			System.out.println("2. Check all student information");
			System.out.println("3. Insert student information");
			System.out.println("4. Modify student information");
			System.out.println("5. Delete student information");
			System.out.println("6. Exit");

			str = scanner.next();
			if(str.equals("1")) {
				System.out.println("Please input student number.");
				String num = scanner.next();
				select(ctx, num);
			} else if(str.equals("2")) {
				selectAll(ctx);
			} else if(str.equals("3")) {
				System.out.println("Please input new student info (sNum, sId, sPw, sName, sAge, sGender, sMajor).");
				String num = scanner.next();
				String id = scanner.next();
				String pw = scanner.next();
				String name = scanner.next();
				int age = scanner.nextInt();
				String gender = scanner.next();
				String major = scanner.next();
				register(ctx, num, id, pw, name, age, gender, major);
			} else if(str.equals("4")) {
				System.out.println("Please input student number you want to modified.");
				String num = scanner.next();
				Student student = select(ctx, num);

				System.out.println("============================");
				System.out.println("1. sId");
				System.out.println("2. sPw");
				System.out.println("3. sName");
				System.out.println("4. sAge");
				System.out.println("5. sGender");
				System.out.println("6. sMajor");
				System.out.println("============================");
				System.out.println("Please choose one attribute you want to modified.");

				int choose = scanner.nextInt();

				System.out.println("Please input revised data.");
				String revisedData = scanner.next();

				if(choose == 1) {
					student.setsId(revisedData);
				} else if(choose == 2) {
					student.setsPw(revisedData);
				} else if(choose == 3) {
					student.setsName(revisedData);
				} else if(choose == 4) {
					student.setsAge(Integer.parseInt(revisedData));
				} else if(choose == 5) {
					student.setsGender(revisedData);
				} else if(choose == 6) {
					student.setsMajor(revisedData);
				} else {
					System.out.println("[Choose Error]");
				}

				modify(ctx, student);
			} else if(str.equals("5")) {
				System.out.println("Please input student number you want to delete.");
				String num = scanner.next();
				delete(ctx, num);
			} else if(str.equals("6")) {
				System.out.println("Program exit.");
				break;
			}
		}

		scanner.close();
		ctx.close();
	}

	public static void register(AnnotationConfigApplicationContext ctx, String sNum, String sId, String sPw, String sName, int sAge, String sGender, String sMajor) {
		StudentRegisterService registerService
			= ctx.getBean("studentRegisterService", me.java.ems.service.StudentRegisterService.class);
		Student student = new Student(sNum, sId, sPw, sName, sAge, sGender, sMajor);
		registerService.register(student);
	}
	public static void modify(AnnotationConfigApplicationContext ctx, Student student) {
		StudentModifyService modifyService
			= ctx.getBean("studentModifyService", me.java.ems.service.StudentModifyService.class);
		modifyService.modify(student);
		System.out.println("Student's info modify finished. ");
		System.out.println();
		System.out.println("Please check modified student's info.");
		select(ctx, student.getsNum());
	}
	public static void delete(AnnotationConfigApplicationContext ctx, String num) {
		StudentDeleteService deleteService
			= ctx.getBean("studentDeleteService", me.java.ems.service.StudentDeleteService.class);
		Student student = select(ctx, num);
		deleteService.delete(student);
		System.out.println("Student's info delete finished.");
	}
	public static Student select(AnnotationConfigApplicationContext ctx, String sNum) {
		System.out.println("Student [" + sNum + "]'s info");
		StudentSelectService selectService
			= ctx.getBean("studentSelectService", me.java.ems.service.StudentSelectService.class);
		Student student = selectService.select(sNum);
		System.out.print("sNum:" + student.getsNum() + "\t");
		System.out.print("|sId:" + student.getsId() + "\t");
		System.out.print("|sPw:" + student.getsPw() + "\t");
		System.out.print("|sName:" + student.getsName() + "\t");
		System.out.print("|sAge:" + student.getsAge() + "\t");
		System.out.print("|sGender:" + student.getsGender() + "\t");
		System.out.println("|sMajor:" + student.getsMajor() + "\t");
		return student;
	}
	public static void selectAll(AnnotationConfigApplicationContext ctx) {
		StudentSelectAllService selectAllService
			= ctx.getBean("studentSelectAllService", me.java.ems.service.StudentSelectAllService.class);
		Map<String, Student> studentAll = selectAllService.selectAll();
		Set<String> keys = studentAll.keySet();
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Student student = studentAll.get(key);
			System.out.print("sNum:" + student.getsNum() + "\t");
			System.out.print("|sId:" + student.getsId() + "\t");
			System.out.print("|sPw:" + student.getsPw() + "\t");
			System.out.print("|sName:" + student.getsName() + "\t");
			System.out.print("|sAge:" + student.getsAge() + "\t");
			System.out.print("|sGender:" + student.getsGender() + "\t");
			System.out.println("|sMajor:" + student.getsMajor() + "\t");

		}
	}

}
