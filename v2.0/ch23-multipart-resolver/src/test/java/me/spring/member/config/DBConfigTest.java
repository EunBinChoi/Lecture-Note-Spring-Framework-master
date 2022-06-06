package me.spring.member.config;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import me.spring.config.RootConfig;


@ContextConfiguration(classes = { RootConfig.class }) // 스프링 설정 목록을 통해 스프링 컨텍스트 생성
@RunWith(SpringJUnit4ClassRunner.class) // 스프링의 SpringJUnit4ClassRunner를 이용해서 테스트 실행
@EnableTransactionManagement // 트랜잭션 처리를 허용해주는 어노테이션
public class DBConfigTest {
	@Autowired JdbcTemplate jdbcTemplate;

	@Test
	public void databaseConnectionTest() throws SQLException {
		Connection conn = jdbcTemplate.getDataSource().getConnection();
		assertThat(conn, notNullValue());
	}
}
