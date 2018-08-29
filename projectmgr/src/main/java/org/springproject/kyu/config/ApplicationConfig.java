package org.springproject.kyu.config;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages= {"org.springproject.kyu.dao",
							  "org.springproject.kyu.service"})
@Import({DBConfig.class,AspectConfig.class})

public class ApplicationConfig {
	


}
