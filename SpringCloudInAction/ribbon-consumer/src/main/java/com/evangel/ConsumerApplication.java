package com.evangel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.evangel.hystrix.command.HystrixRequestContextServletFilter;

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerApplication {
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	// java.lang.IllegalStateException: Request caching is not available.
	// Maybe you need to initialize the HystrixRequestContext?
	@Bean
	public HystrixRequestContextServletFilter contextServletFilter() {
		return new HystrixRequestContextServletFilter();
	}
}
// http://localhost:9000/users/1
// http://localhost:9000/usersAsync/1
// http://localhost:9000/usersObservable/1
// http://localhost:9000/users