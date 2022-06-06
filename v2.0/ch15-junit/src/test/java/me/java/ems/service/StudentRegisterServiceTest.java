package me.java.ems.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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


@ContextConfiguration(classes = { AppConfig.class }) // 스프링 설정 목록을 통해 스프링 컨텍스트 생성
@RunWith(SpringJUnit4ClassRunner.class) // 스프링의 SpringJUnit4ClassRunner를 이용해서 테스트 실행
@EnableTransactionManagement // 트랜잭션 처리를 허용해주는 어노테이션
public class StudentRegisterServiceTest {
	@Autowired
	private StudentRegisterService studentRegisterService;

	/////////////////////////////// Test Case ///////////////////////////////
	// 생길 수 있는 오류
	// 1) DB에 이미 존재하는 회원을 등록하려고 할 때 (PK 중복)
	// 2) register 하기 위해서 Student 객체가 필요한데 null일 때
	// 3) 정상적으로 작동
	/////////////////////////////////////////////////////////////////////////
	// DataAccessException (JdbcTemplate의 오류)
	//
	// 1) BadSqlGrammarException: SQL 문법 오류
	// 2) DataAccessResourceFailureException: DB 커넥션 객체 얻지 못함
	// 3) DataIntegrityViolationException: 제약 조건 위반
	// 4) DuplicateKeyException: 중복 키
	/////////////////////////////////////////////////////////////////////////

	private Student testRegisterStudent_AlreadyStudentInDataBase_ExceptedException;
	private Student testRegisterStudent_NullStudent_ExceptedException;
	private Student testRegisterStudent_RunsOk;

	@Before
	public void registerTestCase() {
		testRegisterStudent_AlreadyStudentInDataBase_ExceptedException
		= new Student("1", "test", "1234", "tester", 30, "W", "Computer");

		testRegisterStudent_NullStudent_ExceptedException = null;

		testRegisterStudent_RunsOk
		= new Student("2", "test", "1234", "tester", 30, "W", "Computer");
	}

	// 1) DB에 이미 존재하는 회원을 등록하려고 할 때 (PK 중복)
	@Transactional
	@Test
	public void registerServiceTest_for_testRegisterStudent_AlreadyStudentInDataBase_ExceptedException() {
		int registerReturns = studentRegisterService.register(testRegisterStudent_AlreadyStudentInDataBase_ExceptedException);
		assertThat(registerReturns, equalTo(1));
		int registerAlreadyStudentReturns = studentRegisterService.register(testRegisterStudent_AlreadyStudentInDataBase_ExceptedException);
		assertThat(registerAlreadyStudentReturns, equalTo(-1));
	}

	// 2) register 하기 위해서 Student 객체가 필요한데 null일 때
	@Transactional
	@Test(expected = NullPointerException.class)
	public void registerServiceTest_for_testRegisterStudent_NullStudent_ExceptedException() {
		int registerReturns = studentRegisterService.register(testRegisterStudent_NullStudent_ExceptedException);
	}

	// 3) 정상적으로 작동
	@Transactional
	@Test
	public void registerServiceTest_for_testRegisterStudent_RunsOk() {
		int registerReturns = studentRegisterService.register(testRegisterStudent_RunsOk);
		assertThat(registerReturns, equalTo(1));
	}

}
