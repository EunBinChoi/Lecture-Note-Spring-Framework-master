package me.spring.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration // applicationContext.xml 대체하는 자바 클래스다!
// controller, configuration을 제외한 나머지 component 등록
@ComponentScan(basePackages = { "me.spring" })
@PropertySource("datasource.properties")
public class AppConfig implements EnvironmentAware {
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

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSourceCP());
		return jdbcTemplate;
	}

	@Bean(name = "transactionManager")
	public org.springframework.jdbc.datasource.DataSourceTransactionManager dataSourceTransactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSourceCP());
		return dataSourceTransactionManager;
	}

	@Bean(name = "propertySourcesPlaceholderConfigurer")
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}


}
