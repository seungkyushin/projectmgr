package org.springproject.kyu.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springproject.kyu.mapper.VisiterMapper;
@Configuration
@EnableTransactionManagement
@MapperScan("org.springproject.kyu.mapper") //< mybatis를 위한 @Select, @Insert 어노테이션을 스캔하기 위해
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
	
	/*
	 * DataSoruce를 사용하여 MYSQL DB의 connection pool을 만든다.
	 * mysql connection j로 db에 연결하고 DataSoource가 미리 connection을 여러개 만들어 보관한다.
	 * 이 프로젝트에 사용하고 있는 DataSource는 commons-dbcp2 이다.
	 * */
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



	/*
	 * sqlSessionFactory를 생성한다.
	 * 마이바티스 스프링 연동모듈에서, SqlSessionFactoryBean은 SqlSessionFactory를 만들기 위해 사용된다. 
	 * SqlSessionFactory는 DataSource를 필요로 하는 것을 알아둘 필요가 있다. 어떤 DataSource도 상관없지만 다른 스프링의 데이터베이스 연결과 동일하게 설정되어야 한다.
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	  <property name="dataSource" ref="dataSource" />
	  <property name="configLocation" value="classpath:/mybatis-config.xml" />
	</bean>
	*/
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(jdbcConnection());
		//sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/**/*Mapper.xml"));
		return (SqlSessionFactory)sessionFactory.getObject();
	}
	

	 
	@Bean
	public DateFormat dataFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
}
