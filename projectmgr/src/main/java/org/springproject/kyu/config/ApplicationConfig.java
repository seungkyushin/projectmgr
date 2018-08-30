package org.springproject.kyu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/* Tomcat이 Web.xml로 통해 root WebApplicationContext를 등록할때 참고되어 설정되는 Config Class*/
@Configuration
/* ComponentScan을 통해 기입된 경로의 Component 어노테이션들을 Container에 등록한다 */
@ComponentScan(basePackages= {"org.springproject.kyu.dao",
							  "org.springproject.kyu.service"})
/* 기입된 class를 기준으로 @Configuration 어노테이션을 확인하고  설정 작업을 하게 된다.*/
@Import({DBConfig.class,AspectConfig.class})

public class ApplicationConfig {
}
