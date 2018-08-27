package org.springproject.kyu.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer{

	private String driverClassName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/projectmgr_db?useUnicode=true&characterEncoding=utf8";
	private String username = "root";
	private String password = "Dkagh1234.";
	
	
	//< XML에 설정하지 않고 TransactionManagementConfigurer Interface를 구현하여 TransactionManager를 설정한다.
	//< TransactionManager는 Bean으로 등록해야하며 DataSourceTransactionManager를 사용
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManager();
	}
	
	//< DB 관련 Beans
	@Bean
	public DataSource jdbcConnection() {
		BasicDataSource dbs = new BasicDataSource();
		dbs.setDriverClassName(driverClassName);
		dbs.setUrl(url);
		dbs.setUsername(username);
		dbs.setPassword(password);
		return dbs;
	}
	
	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(jdbcConnection());
	}
	
	@Bean
	public DateFormat dataFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
}
