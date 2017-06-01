package com.evangel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReadingListApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReadingListApplication.class, args);
	}
}
// http://localhost:8000/readinglist
// 3.1 覆盖 Spring Boot 自动配置
// http://localhost:8000/reader/readinglist
