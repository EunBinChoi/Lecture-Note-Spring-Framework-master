package me.spring.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


/* DB (dataSource, jdbcTemplate), Service, Dao (모든 d.s에서 공통된 환경 조성) */
@Configuration
@Import(value = AppConfig.class)
@EnableTransactionManagement
@ComponentScan(basePackages = "me.spring",
		useDefaultFilters = true, // spring에서 관리하고 있는 spring stereotype들을 다 등록!
		excludeFilters = {
			@Filter(type = FilterType.ANNOTATION, 
					value = { Controller.class, Configuration.class }) })
@PropertySource("datasource.properties")
public class RootConfig implements EnvironmentAware { // root.xml, AppConfig.class
	Environment environment;


	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		this.environment = environment;
	}
	
	@Bean(name = "dataSourceCP")
	public HikariDataSource dataSourceCP() {
		com.zaxxer.hikari.HikariConfig config = new HikariConfig();
		config.setPoolName(environment.getProperty("hikari.config.poolName"));
		config.setMaximumPoolSize(Integer.parseInt(environment.getProperty("hikari.config.maximumPoolSize")));
		config.setIdleTimeout(Long.parseLong(environment.getProperty("hikari.config.idleTimeout")));
		config.setDriverClassName(environment.getProperty("dataSource.driverClassName"));
		config.setJdbcUrl(environment.getProperty("dataSource.url"));
		config.setUsername(environment.getProperty("dataSource.username"));
		config.setPassword(environment.getProperty("dataSource.password"));

		com.zaxxer.hikari.HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}

	@Bean(name = "jdbcTemplate") // vs mybatis (성능면에서 좀 더 좋음, mapping이 좀 더 편함)
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSourceCP());
		return jdbcTemplate;
	}

	@Bean(name = "transactionManager") // Test 환경에서 @Transactional (rollback)
	public org.springframework.jdbc.datasource.DataSourceTransactionManager dataSourceTransactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSourceCP());
		return dataSourceTransactionManager;
	}

}