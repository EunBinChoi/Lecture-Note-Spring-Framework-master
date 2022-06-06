package me.java.ems.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import me.java.ems.config.AppConfig;
import me.java.ems.entity.Student;



@ContextConfiguration(classes = {AppConfig.class}) // 스프링 설정 목록을 통해 스프링 컨텍스트 생성
@RunWith(SpringJUnit4ClassRunner.class) // 스프링의 SpringJUnit4ClassRunner를 이용해서 테스트 실행
@EnableTransactionManagement // 트랜잭션 처리를 허용해주는 어노테이션
public class StudentSelectAllServiceTest {
	@Autowired private StudentSelectAllService studentSelectAllService;
	@Autowired private StudentRegisterService studentRegisterService; // studentSelectAllService를 테스트 하기 위함

	/////////////////////////////// Test Case ///////////////////////////////
	// 생길 수 있는 오류
	// 1) 정상적으로 작동
	/////////////////////////////////////////////////////////////////////////
	// DataAccessException (JdbcTemplate의 오류)
	//
	// 1) BadSqlGrammarException: SQL 문법 오류
	// 2) DataAccessResourceFailureException: DB 커넥션 객체 얻지 못함
	// 3) DataIntegrityViolationException: 제약 조건 위반
	// 4) DuplicateKeyException: 중복 키
	/////////////////////////////////////////////////////////////////////////
	private List<Student> testSelectAllStudent_RunsOk;

	@Before
	public void selectTestCase() {
		testSelectAllStudent_RunsOk
		= Arrays.asList(
				new Student("1", "test", "1234", "tester", 30, "W", "Computer"),
				new Student("2", "test", "1234", "tester", 30, "W", "Computer"),
				new Student("3", "test", "1234", "tester", 30, "W", "Computer"));
	}


	// 1) 정상적으로 작동
	@Transactional
	@Test
	public void selectAllServiceTest_for_testSelectAllStudent_RunsOk() {
		for (Student element : testSelectAllStudent_RunsOk) {
			int registerReturns = studentRegisterService.register(element);
			assertThat(registerReturns, equalTo(1));
		}
		List<Student> studentLists = studentSelectAllService.selectAll();
		assertThat(studentLists, notNullValue());
	}

}
