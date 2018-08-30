package org.springproject.kyu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springproject.kyu.service.LogService;

@Component
@Aspect
public class LogAspect {
	
	@Autowired
	LogService logService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Around("execution(* org.springproject.kyu.dao.*.insert*(..)) "
			+ "or execution(* org.springproject.kyu.dao.*.update*(..))"
			+ "or execution(* org.springproject.kyu.dao.*.delete*(..))" )
	public Object logPrint(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("{} | {} | {}",pjp.getTarget().toString(),
				pjp.getSignature().getName(),pjp.getArgs());

		return pjp.proceed();
	}
	/*@After("execution(* add*(..))")
	public void after(JoinPoint jp){
		System.out.println( jp.getSignature().getName() + "After");
	}
	@Before("execution(* add*(..))")
	public void before(JoinPoint jp){
		System.out.println(jp.getSignature().getName() + "before");
	}*/
	
/*	 @AfterThrowing(pointcut="execution(* add*(..))", throwing="ex")
	 public void afterThrowing(JoinPoint joinPoint, Throwable ex){
		 System.out.println("log Aspect Error");
		 logger.error("{}",ex.toString());
	  //System.out.println("ex message : " + ex.toString());
	 }
*/
/*	@AfterReturning("execution(* add*(..))")
	public void afterReturning(JoinPoint jp){
	
		System.out.println(jp.getSignature().getName() + " afterReturning");
	}*/
}

