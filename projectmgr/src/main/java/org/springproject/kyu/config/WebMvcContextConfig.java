package org.springproject.kyu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.springproject.kyu.interceptor.AuthenticationInterceptor;
import org.springproject.kyu.interceptor.PageUtilInterceptor;


/*
 * Spring 프레임워크의 어노 테이션 기반 트랜잭션 관리를 사용할 수 있도록 한다.
 * <tx:annotation-driven>
 */
@Configuration
@EnableWebMvc //<DispatcherServlet의 RequestMappingHandlerMapping, RequestMappingHandlerAdapter, ExceptionHandlerExceptionResolver, MessageConverter 등 Web에 필요한 빈들을 대부분 자동으로 설정해준다.
@ComponentScan(basePackages = { "org.springproject.kyu.controller", "org.springproject.kyu.exception" })
public class WebMvcContextConfig extends WebMvcConfigurerAdapter{

	//< Resource의 요청이 들어오면 ResourceHandler에게 설정된 경로부터 찾으라고 알려준다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/css/**").addResourceLocations("/assets/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/img/**").addResourceLocations("/assets/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets//js/**").addResourceLocations("/assets/js/").setCachePeriod(31556926);
	}


	//<
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("main");
		registry.addViewController("/main").setViewName("main");
	}

	/*
	 DefaultServletHandler를 사용하기위해 오버라이딩 한다.
	 사용해야하는 이유는  DispatcherServlet의 url-parren을 '/'로하여 모든 요청을 처리하도록 설정했기 때문에
	 CSS, JavaScript, image등의 요청도 DispatcherServlet이 받아 작업하게 된다.
	 당연히 리소스에 대한 URL처리는 해주고 있지 않기 때문에 문제가 발생된다. 
	 그리하여 이런 정적인 리소스를 처리하기 위해서 DefaultServlet을 사용 한다.
	 아래의 메소드처럼 DefaultServletHandling을 활성화하게 되면 DispatcherServlet이 처리 못하는
	 DefaultServlet을 알아서 처리하게 된다.(비 활성화시 리소스관련 url응답 실패)
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	  	configurer.enable();
	}
	 
	
/*	<mvc:interceptors>
    <bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor"/>
    <mvc:interceptor>
        <mvc:mapping path="/**"/>
        <mvc:exclude-mapping path="/admin/**"/>
        <bean class="org.springframework.web.servlet.theme.ThemeChangeInterceptor"/>
    </mvc:interceptor>
    <mvc:interceptor>
        <mvc:mapping path="/secure/*"/>
        <bean class="org.example.SecurityInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>
*/
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthenticationInterceptor())
		.addPathPatterns("/profile*")
		.addPathPatterns("/comment*")
		.excludePathPatterns("/api/**");
		
		registry.addInterceptor(new PageUtilInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/api/**");
	}
	    
	//
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		org.springframework.web.multipart.commons.CommonsMultipartResolver mpResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver ();
		mpResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10 (KB,MB) 10MB 제한
		return mpResolver;
		
	}
	

}
