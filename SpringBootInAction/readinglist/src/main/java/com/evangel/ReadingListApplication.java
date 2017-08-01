package com.evangel;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//2.1.1
//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories
public class ReadingListApplication extends WebMvcConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(ReadingListApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/readingList");
		registry.addViewController("/login").setViewName("login");
	}

	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new ReaderHandlerMethodArgumentResolver());
	}
}
// http://localhost:8000/readingList
// 3.1 覆盖 Spring Boot 自动配置
// http://localhost:8000
// 1.2.2 使用 Spring Initializr 初始化 Spring Boot 项目
// http://start.spring.io
