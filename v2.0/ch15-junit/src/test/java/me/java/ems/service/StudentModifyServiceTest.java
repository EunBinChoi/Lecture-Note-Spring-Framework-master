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


@ContextConfiguration(classes = {AppConfig.class}) // 스프링 설정 목록을 통해 스프링 컨텍스트 생성
@RunWith(SpringJUnit4ClassRunner.class) // 스프링의 SpringJUnit4ClassRunner를 이용해서 테스트 실행
@EnableTransactionManagement // 트랜잭션 처리를 허용해주는 어노테이션
public class StudentModifyServiceTest {
	@Autowired private StudentModifyService studentModifyService;
	@Autowired private StudentRegisterService studentRegisterService; // StudentModifyService를 테스트 하기 위함

	/////////////////////////////// Test Case ///////////////////////////////
	// 생길 수 있는 오류
	// 1) DB에 없는 회원을 수정하려고 할 때
	// 2) modify 하기 위해서 Student 객체가 필요한데 null일 때
	// 3) modify 하기 위해서 sNum (PK)가 필요한데 null일 때
	// 4) 정상적으로 작동
	/////////////////////////////////////////////////////////////////////////
	// DataAccessException (JdbcTemplate의 오류)
	//
	// 1) BadSqlGrammarException: SQL 문법 오류
	// 2) DataAccessResourceFailureException: DB 커넥션 객체 얻지 못함
	// 3) DataIntegrityViolationException: 제약 조건 위반
	// 4) DuplicateKeyException: 중복 키
	/////////////////////////////////////////////////////////////////////////

	private Student testModifyStudent_NoneStudentInDataBase_ExceptedException;
	private Student testModifyStudent_NullStudent_ExceptedException;
	private Student testModifyStudent_NullPK_ExceptedException;
	private Student testModifyStudent_RunsOk;


	@Before
	public void modifyTestCase() {
		testModifyStudent_NoneStudentInDataBase_ExceptedException
		= new Student("1", "test", "1234", "tester", 30, "W", "Computer");

		testModifyStudent_NullStudent_ExceptedException = null;

		testModifyStudent_NullPK_ExceptedException = new Student();
		testModifyStudent_NullPK_ExceptedException.setsNum(null);

		testModifyStudent_RunsOk
		= new Student("2", "test", "1234", "tester", 30, "W", "Computer");
	}

	// 1) DB에 없는 회원을 수정하려고 할 때
	@Transactional
	@Test
	public void modifyServiceTest_for_testModifyStudent_NoneStudentInDataBase_ExceptedException() {
		int modifyReturns = studentModifyService.modify(testModifyStudent_NoneStudentInDataBase_ExceptedException);
		assertThat(modifyReturns, equalTo(-1));
	}


	// 2) modify 하기 위해서 Student 객체가 필요한데 null일 때
	@Transactional
	@Test(expected = NullPointerException.class)
	public void modifyServiceTest_for_testModifyStudent_NullStudent_ExceptedException() {
		int modifyReturns = studentModifyService.modify(testModifyStudent_NullStudent_ExceptedException);
	}

	// 3) modify 하기 위해서 sNum (PK)가 필요한데 null일 때
	@Transactional
	@Test
	public void modifyServiceTest_for_testModifyStudent_NullPK_ExceptedException() {
		int modifyReturns = studentModifyService.modify(testModifyStudent_NullPK_ExceptedException);
		assertThat(modifyReturns, equalTo(-1));
	}

	// 4) 정상적으로 작동
	@Transactional
	@Test
	public void modifyServiceTest_for_testModifyStudent_RunsOk() {
		int registerReturns = studentRegisterService.register(testModifyStudent_RunsOk);
		assertThat(registerReturns, equalTo(1));
		int modifyReturns = studentModifyService.modify(testModifyStudent_RunsOk);
		assertThat(modifyReturns, equalTo(1));

	}
}
