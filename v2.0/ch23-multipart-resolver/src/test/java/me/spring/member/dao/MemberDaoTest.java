package me.spring.member.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import me.spring.config.RootConfig;
import me.spring.member.beans.MemberEntity;


@ContextConfiguration(classes = { RootConfig.class }) // 스프링 설정 목록을 통해 스프링 컨텍스트 생성
@RunWith(SpringJUnit4ClassRunner.class) // 스프링의 SpringJUnit4ClassRunner를 이용해서 테스트 실행
@EnableTransactionManagement // 트랜잭션 처리를 허용해주는 어노테이션
public class MemberDaoTest {
	@Autowired
	private MemberDao memberDao;

	@Ignore
	@Test
	@Transactional
	public void insertTest() throws Exception {
		int resultInsert = memberDao.insert(new MemberEntity("d", "d", "d", "d@naver.com", "010-0000-0000"));
		assertThat(resultInsert, is(equalTo(1)));
	}

	@Ignore
	@Test
	@Transactional
	public void updateTest() throws Exception {
		int resultInsert = memberDao.insert(new MemberEntity("d", "d", "d", "d@naver.com", "010-0000-0000"));
		assertThat(resultInsert, is(equalTo(1)));
		int resultUpdate = memberDao.update(new MemberEntity("d", "dddd", "dddddd", "d@naver.com", "010-0000-0000"));
		assertThat(resultUpdate, is(equalTo(1)));
	}

	@Ignore
	@Test
	@Transactional
	public void deleteTest() throws Exception {
		int resultInsert = memberDao.insert(new MemberEntity("d", "d", "d", "d@naver.com", "010-0000-0000"));
		assertThat(resultInsert, is(equalTo(1)));
		int resultDelete = memberDao.delete("d");
		assertThat(resultDelete	, is(equalTo(1)));
	}

	@Ignore
	@Test
	@Transactional
	public void selectTest() throws Exception {
		int resultInsert = memberDao.insert(new MemberEntity("d", "d", "d", "d@naver.com", "010-0000-0000"));
		assertThat(resultInsert, is(equalTo(1)));
		MemberEntity resultSelect = memberDao.select("d");
		assertThat(resultSelect, notNullValue());
	}

	@Test
	@Transactional
	public void selectAllTest() throws Exception {
		List<MemberEntity> resultSelectAll = memberDao.selectAll();
		assertThat(resultSelectAll, notNullValue());
	}

}
