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


@Configuration //< Spring Container가 스캔하면 해당 파일은 설정파일로 인식한다.
@EnableTransactionManagement //< 
@MapperScan("org.springproject.kyu.mapper") //< mybatis를 위한 @Select, @Insert 어노테이션을 스캔하기 위해
public class DBConfig implements TransactionManagementConfigurer{

	private String driverClassName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/projectmgr_db?useUnicode=true&characterEncoding=utf8";
	private String username = "root";
	private String password = "Dkagh1234.";
	
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
	
	
	/*
	 * 트랜잭션을 관리하는 매니저를 설정한다.
	 * 트랜잭션의 매니저의 추상화 클래스인 PlatformTransactionManager를 Bean으로 등록 해야하여
	 * 실제 구현은 DataSourceTransactionManager로 한다.
	 * TransactionManagementConfigurer Interface의 메소드인 annotationDrivenTransactionManager에 
	 * 트랜잭션 매니저를 등록해주어야 한다.
	 * */
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(jdbcConnection());
	}
	
	
	//< XML에 설정하지 않고 TransactionManagementConfigurer Interface를 구현하여 TransactionManager를 설정한다.
	//< TransactionManager는 Bean으로 등록해야하며 DataSourceTransactionManager를 사용
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManager();
	}
	
	/*
	 * Spring에서 지원하는 JdbcTemplate은 3가지가 있다. JdbcTemplate, NamedParameterJdbcTemplate, SimpleJdbcTemplate
	 * 생성시 DataSource가 필요하며 하는 역할은 쿼리를 이용해 DB의 데이터를 받아온다.
	 * 그중 NamedParameterJdbcTemplate는 인덱스 기반의 파라미터가 아닌 이름 기반의 파라미터를 설정할 수 있도록 해주는 템플릿 클래스이다.
	 * */
	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}




	/*
	 * Mabatis는 SqlSession이라는 자바 인터페이스를 이용해서 명령어 실행, Mapper획득, 트랜잭션 관리등을 맡게된다.
	 * Mybatis-Spring 연동시, SqlSessionFactory는 SqlSessionFactoryBean을 통해 생성되며 DataSource를 필요로 한다.
	 * 어떤 DataSource도 상관없지만 다른 스프링의 데이터베이스 연결과 동일하게 설정되어야 한다.
	 * Mybatis-Spring 연동시, SqlSession의 실제 구현체는 SqlSessionTemplate이다. 
	 * SqlSessionTemplate의 역할은 자통 트랜잭션관리, SqlSessionm의 자동 메모리 해제
	 * SqlSession은 쓰레드가 안전하지 않기 떄문에 한개 session만 open 되었다가 사용완료 후 close를 해줘야한다.
	 * SqlSessingFactory는 SqlSession을 만드는 역할 및 관리한다.
	 * 
	 *  XML 설정
	  <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	   <property name="dataSource" ref="dataSource" />
	   <property name="configLocation" value="classpath:/mybatis-config.xml" />
	  </bean>
      <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
		<constructor-arg ref="sqlSessionFactory"/>
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
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
	@Bean
	public DateFormat dataFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
}
