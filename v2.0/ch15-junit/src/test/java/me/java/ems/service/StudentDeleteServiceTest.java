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
public class StudentDeleteServiceTest {
	@Autowired
	private StudentDeleteService studentDeleteService;
	@Autowired
	private StudentRegisterService studentRegisterService; // StudentDeleteService를 테스트 하기 위함

	/////////////////////////////// Test Case ///////////////////////////////
	// 생길 수 있는 오류
	// 1) DB에 없는 회원을 삭제하려고 할 때
	// 2) delete 하기 위해서 Student 객체가 필요한데 null일 때
	// 3) delete 하기 위해서 sId가 필요한데 null일 때
	// 4) delete 하기 위해서 sPw가 필요한데 null일 때
	// 5) 정상적으로 작동
	/////////////////////////////////////////////////////////////////////////
	// DataAccessException (JdbcTemplate의 오류)
	//
	// 1) BadSqlGrammarException: SQL 문법 오류
	// 2) DataAccessResourceFailureException: DB 커넥션 객체 얻지 못함
	// 3) DataIntegrityViolationException: 제약 조건 위반
	// 4) DuplicateKeyException: 중복 키
	/////////////////////////////////////////////////////////////////////////

	private Student testDeleteStudent_NoneStudentInDataBase_ExceptedException;
	private Student testDeleteStudent_NullStudent_ExceptedException;
	private Student testDeleteStudent_NullsId_ExceptedException;
	private Student testDeleteStudent_NullsPw_ExceptedException;
	private Student testDeleteStudent_RunsOk;

	@Before
	public void deleteTestCase() {
		testDeleteStudent_NoneStudentInDataBase_ExceptedException
		= new Student("1", "test", "1234", "tester", 30, "W", "Computer");

		testDeleteStudent_NullStudent_ExceptedException = null;

		testDeleteStudent_NullsId_ExceptedException = new Student();
		testDeleteStudent_NullsId_ExceptedException.setsNum("1");
		testDeleteStudent_NullsId_ExceptedException.setsId(null);
		testDeleteStudent_NullsId_ExceptedException.setsPw("1234");

		testDeleteStudent_NullsPw_ExceptedException = new Student();
		testDeleteStudent_NullsPw_ExceptedException.setsNum("1");
		testDeleteStudent_NullsPw_ExceptedException.setsId("test");
		testDeleteStudent_NullsPw_ExceptedException.setsPw(null);

		testDeleteStudent_RunsOk
		= new Student("2", "test", "1234", "tester", 30, "W", "Computer");
	}

	// 1) DB에 없는 회원을 삭제하려고 할 때
	@Transactional // 하나의 트랜잭션 단위로 보겠다는 의미 (test인 경우에는 자동 rollback)
	@Test
	public void deleteServiceTest_for_testDeleteStudent_NoneStudentInDataBase_ExceptedException() {
		int deleteReturns = studentDeleteService.delete(testDeleteStudent_NoneStudentInDataBase_ExceptedException);
		assertThat(deleteReturns, equalTo(-1));
	}

	// 2) delete 하기 위해서 Student 객체가 필요한데 null일 때
	@Transactional // 하나의 트랜잭션 단위로 보겠다는 의미 (test인 경우에는 자동 rollback)
	@Test(expected = NullPointerException.class)
	public void deleteServiceTest_for_testDeleteStudent_NullStudent_ExceptedException() {
		int deleteReturns = studentDeleteService.delete(testDeleteStudent_NullStudent_ExceptedException);
	}

	// 3) delete 하기 위해서 회원 아이디가 필요한데 null일 때
	@Transactional // 하나의 트랜잭션 단위로 보겠다는 의미 (test인 경우에는 자동 rollback)
	@Test
	public void deleteServiceTest_for_testDeleteStudent_NullId_ExceptedException() {
		int deleteReturns = studentDeleteService.delete(testDeleteStudent_NullsId_ExceptedException);
		assertThat(deleteReturns, equalTo(-1));
	}

	// 4) delete 하기 위해서 회원 비밀번호가 필요한데 null일 때
	@Transactional // 하나의 트랜잭션 단위로 보겠다는 의미 (test인 경우에는 자동 rollback)
	@Test
	public void deleteServiceTest_for_testDeleteStudent_NullPw_ExceptedException() {
		int deleteReturns = studentDeleteService.delete(testDeleteStudent_NullsPw_ExceptedException);
		assertThat(deleteReturns, equalTo(-1));
	}

	// 5) 정상적으로 작동
	@Transactional // 하나의 트랜잭션 단위로 보겠다는 의미 (test인 경우에는 자동 rollback)
	@Test
	public void deleteServiceTest_for_testDeleteStudent_RunsOk() {
		int registerReturns = studentRegisterService.register(testDeleteStudent_RunsOk);
		assertThat(registerReturns, equalTo(1));
		int deleteReturns = studentDeleteService.delete(testDeleteStudent_RunsOk);
		assertThat(deleteReturns, equalTo(1));
	}

}
