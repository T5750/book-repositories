package com.evangel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
public class HelloApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}
}
// chapter2
// http://localhost:8081/health
// http://localhost:8081/autoconfig
// http://localhost:8081/beans
// http://localhost:8081/configprops
// http://localhost:8081/env
// http://localhost:8081/mappings
// http://localhost:8081/info
// http://localhost:8081/metrics
// http://localhost:8081/metrics/mem.free
// http://localhost:8081/dump
// http://localhost:8081/trace
// POST http://localhost:8081/shutdown
// POST http://localhost:8081/restart
// chapter3
// 端点配置
// http://localhost:8081/hello/health
// http://localhost:8081/checkHealth