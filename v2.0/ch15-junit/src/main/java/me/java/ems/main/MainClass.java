package me.java.ems.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import me.java.ems.config.AppConfig;
import me.java.ems.entity.Student;
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
		/*String[] sNums = { "H39r8djakndfae32", "H39asdfaelu42o23", "H39iiemamca8w9h4", "H39lkmn754fghia7",
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
		*/
		/////////////////////////////////////////////////////////////////////////////////////////////////

		// 스프링 컨테이너가 생김 (그 안에 있는 객체가 생기고 의존 주입이 일어남)
		// GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("applicationContext.xml");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		// EMSInfoService 출력
		EMSInformation emsInformation = ctx.getBean("emsInformation", EMSInformation.class);
		emsInformation.outputEMSInfo();

		// 학생 정보 등록, 수정, 검색, 전체 검색
		/*for (int i = 0; i < sNums.length; i++) {
			register(ctx, sNums[i], sIds[i], sPws[i], sNames[i], sAges[i], sGenders[i], sMajors[i]);
		}

		System.out.println("\n================================================ Small Test ==================="
				+ "==============================================================================");
		System.out.println("Student \"H39lesvj7544vf89\" info before modified");
		Student student = select(ctx, "H39lesvj7544vf89");
		student.setsMajor("History");
		modify(ctx, student);
		System.out.println("Student \"H39lesvj7544vf89\" info after modified");
		select(ctx, "H39lesvj7544vf89");
		System.out.println("\n=============================================================================="
				+ "==============================================================================");
		System.out.println("\n\n");

		selectAll(ctx);*/

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
			if (str.equals("1")) {
				System.out.println("Please input student number.");
				String num = scanner.next();
				select(ctx, num);
			} else if (str.equals("2")) {
				selectAll(ctx);
			} else if (str.equals("3")) {
				System.out.println("Please input new student info (sNum, sId, sPw, sName, sAge, sGender, sMajor).");
				String num = scanner.next();
				String id = scanner.next();
				String pw = scanner.next();
				String name = scanner.next();
				int age = scanner.nextInt();
				String gender = scanner.next();
				String major = scanner.next();
				register(ctx, num, id, pw, name, age, gender, major);
			} else if (str.equals("4")) {
				System.out.println("Please input student number you want to modified.");
				String num = scanner.next();
				Student modifiedStudent = select(ctx, num);

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
				String revisedData = null;

				System.out.println("Please input revised data.");
				revisedData = scanner.next();
				switch(choose) {
				case 1: modifiedStudent.setsId(revisedData);
				case 2: modifiedStudent.setsPw(revisedData);
				case 3: modifiedStudent.setsName(revisedData);
				case 4: modifiedStudent.setsAge(Integer.parseInt(revisedData));
				case 5: modifiedStudent.setsGender(revisedData);
				case 6: modifiedStudent.setsMajor(revisedData);
				}

				modify(ctx, modifiedStudent);
			} else if (str.equals("5")) {
				System.out.println("Please input student number you want to delete.");
				String num = scanner.next();
				delete(ctx, num);
			} else if (str.equals("6")) {
				System.out.println("Program exit.");
				break;
			}
		}
		scanner.close();
	}

	public static int register(AnnotationConfigApplicationContext ctx, String sNum, String sId, String sPw, String sName,
			int sAge, String sGender, String sMajor) {
		StudentRegisterService registerService = ctx.getBean("registerService", StudentRegisterService.class);
		Student student = new Student(sNum, sId, sPw, sName, sAge, sGender, sMajor);
		int returns = registerService.register(student);
		System.out.println("Students info register finished. ");
		return returns;
	}

	public static int modify(AnnotationConfigApplicationContext ctx, Student student) {
		StudentModifyService modifyService = ctx.getBean("modifyService", StudentModifyService.class);
		int returns = modifyService.modify(student);
		System.out.println("Student's info modify finished. ");
		System.out.println();
		System.out.println("Please check modified student's info.");
		select(ctx, student.getsNum());
		return returns;
	}

	public static int delete(AnnotationConfigApplicationContext ctx, String sNum) {
		StudentDeleteService deleteService = ctx.getBean("deleteService", StudentDeleteService.class);
		Student student = select(ctx, sNum);
		int returns = deleteService.delete(student);
		System.out.println("Student's info delete finished.");
		return returns;
	}

	public static Student select(AnnotationConfigApplicationContext ctx, String sNum) {
		System.out.println("Student [" + sNum + "]'s info");
		StudentSelectService selectService = ctx.getBean("selectService", StudentSelectService.class);
		Student student = selectService.select(sNum);

		if(student == null) {
			System.out.println("Student's info is not available");
			return null;
		}
		System.out.print("sNum:" + student.getsNum() + "\t");
		System.out.print("|sId:" + student.getsId() + "\t");
		System.out.print("|sPw:" + student.getsPw() + "\t");
		System.out.print("|sName:" + student.getsName() + "\t");
		System.out.print("|sAge:" + student.getsAge() + "\t");
		System.out.print("|sGender:" + student.getsGender() + "\t");
		System.out.println("|sMajor:" + student.getsMajor() + "\t");
		return student;
	}

	public static List<Student> selectAll(AnnotationConfigApplicationContext ctx) {
		StudentSelectAllService selectAllService = ctx.getBean("selectAllService", StudentSelectAllService.class);
		List<Student> studentAll = selectAllService.selectAll();

		if(studentAll != null && studentAll.size() > 0) {
			for (Student student : studentAll) {
				System.out.print("sNum:" + student.getsNum() + "\t");
				System.out.print("|sId:" + student.getsId() + "\t");
				System.out.print("|sPw:" + student.getsPw() + "\t");
				System.out.print("|sName:" + student.getsName() + "\t");
				System.out.print("|sAge:" + student.getsAge() + "\t");
				System.out.print("|sGender:" + student.getsGender() + "\t");
				System.out.println("|sMajor:" + student.getsMajor() + "\t");

			}
		}
		return studentAll;
	}

}
