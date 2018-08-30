package org.springproject.kyu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
/* Spring AOP는 Proxy(JDK Dynamic Proxy, CGLIB, AspectJ)에 의해 동작된다.
 * AspectJ 라이브러리를 이용해 AOP를 동작하기 위해 설정한다. 기본 Proxy와는 다르다.
 * 자동으로 ProxyFactory와 ProxyFactoryBean을 생성하고 설정한다.*/
@EnableAspectJAutoProxy
@ComponentScan(basePackages="org.springproject.kyu.aop")

public class AspectConfig {
}
