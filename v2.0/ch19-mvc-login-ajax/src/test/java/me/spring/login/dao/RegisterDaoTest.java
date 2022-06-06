package me.spring.login.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import me.spring.login.beans.RegisterEntity;


@ContextConfiguration(classes = { me.spring.config.AppConfig.class }) // 스프링 설정 목록을 통해 스프링 컨텍스트 생성
@RunWith(SpringJUnit4ClassRunner.class) // 스프링의 SpringJUnit4ClassRunner를 이용해서 테스트 실행
@EnableTransactionManagement // 트랜잭션 처리를 허용해주는 어노테이션
public class RegisterDaoTest {
	@Autowired
	private RegisterDao registerDao;

	@Test
	@Transactional
	public void insertTest() throws Exception {
		int resultInsert = registerDao.insert(new RegisterEntity("d", "d", "d", "d@naver.com", "010-0000-0000"));
		assertThat(resultInsert, is(equalTo(1)));
	}

	@Test
	@Transactional
	public void updateTest() throws Exception {
		int resultInsert = registerDao.insert(new RegisterEntity("d", "d", "d", "d@naver.com", "010-0000-0000"));
		assertThat(resultInsert, is(equalTo(1)));
		int resultUpdate = registerDao.update(new RegisterEntity("d", "dddd", "dddddd", "d@naver.com", "010-0000-0000"));
		assertThat(resultUpdate, is(equalTo(1)));
	}

	@Test
	@Transactional
	public void deleteTest() throws Exception {
		int resultInsert = registerDao.insert(new RegisterEntity("d", "d", "d", "d@naver.com", "010-0000-0000"));
		assertThat(resultInsert, is(equalTo(1)));
		int resultDelete = registerDao.delete("d");
		assertThat(resultDelete	, is(equalTo(1)));
	}

	@Test
	@Transactional
	public void selectTest() throws Exception {
		int resultInsert = registerDao.insert(new RegisterEntity("d", "d", "d", "d@naver.com", "010-0000-0000"));
		assertThat(resultInsert, is(equalTo(1)));
		RegisterEntity resultSelect = registerDao.select("d");
		assertThat(resultSelect, notNullValue());
	}

	@Test
	@Transactional
	public void selectAllTest() throws Exception {
		List<RegisterEntity> resultSelectAll = registerDao.selectAll();
		assertThat(resultSelectAll, notNullValue());
	}

}
