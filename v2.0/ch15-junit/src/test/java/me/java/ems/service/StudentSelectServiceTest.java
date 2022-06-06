package me.java.ems.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
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
public class StudentSelectServiceTest {
	@Autowired private StudentSelectService studentSelectService;
	@Autowired private StudentRegisterService studentRegisterService; // studentSelectService를 테스트 하기 위함

	/////////////////////////////// Test Case ///////////////////////////////
	// 생길 수 있는 오류
	// 1) DB에 없는 회원을 조회하려고 할 때
	// 2) select 하기 위해서 sNum (PK)가 필요한데 null일 때
	// 3) 정상적으로 작동
	/////////////////////////////////////////////////////////////////////////
	// DataAccessException (JdbcTemplate의 오류)
	//
	// 1) BadSqlGrammarException: SQL 문법 오류
	// 2) DataAccessResourceFailureException: DB 커넥션 객체 얻지 못함
	// 3) DataIntegrityViolationException: 제약 조건 위반
	// 4) DuplicateKeyException: 중복 키
	/////////////////////////////////////////////////////////////////////////

	private Student testSelectStudent_NoneStudentInDataBase_ExceptedException;
	private Student testSelectStudent_NullPK_ExceptedException;
	private Student testSelectStudent_RunsOk;


	@Before
	public void selectTestCase() {
		testSelectStudent_NoneStudentInDataBase_ExceptedException
		= new Student("1", "test", "1234", "tester", 30, "W", "Computer");

		testSelectStudent_NullPK_ExceptedException = new Student();
		testSelectStudent_NullPK_ExceptedException.setsNum(null);

		testSelectStudent_RunsOk
		= new Student("2", "test", "1234", "tester", 30, "W", "Computer");
	}

	// 1) DB에 없는 회원을 수정하려고 할 때
	@Transactional
	@Test
	public void selectServiceTest_for_testSelectStudent_NoneStudentInDataBase_ExceptedException() {
		Student selectStudent = studentSelectService.select(testSelectStudent_NoneStudentInDataBase_ExceptedException.getsNum());
		assertThat(selectStudent, nullValue());
	}

	// 2) select 하기 위해서 sNum (PK)가 필요한데 null일 때
	@Transactional
	@Test
	public void selectServiceTest_for_testSelectStudent_NullPK_ExceptedException() {
		Student selectStudent = studentSelectService.select(testSelectStudent_NullPK_ExceptedException.getsNum());
		assertThat(selectStudent, nullValue());
	}

	// 3) 정상적으로 작동
	@Transactional
	@Test
	public void selectServiceTest_for_testSelectStudent_RunsOk() {
		int registerReturns = studentRegisterService.register(testSelectStudent_RunsOk);
		assertThat(registerReturns, equalTo(1));
		Student registerStudent = studentSelectService.select(testSelectStudent_RunsOk.getsNum());
		assertThat(registerStudent, notNullValue());

	}
}
