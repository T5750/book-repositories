package com.evangel.hystrix.command;

import org.springframework.web.client.RestTemplate;

import com.evangel.web.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class UserCommand extends HystrixCommand<User> {
	private RestTemplate restTemplate;
	private Long id;

	public UserCommand(RestTemplate restTemplate, Long id) {
		super(Setter.withGroupKey(
				HystrixCommandGroupKey.Factory.asKey("UserGroup")));
		this.restTemplate = restTemplate;
		this.id = id;
	}

	public UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
		super(setter);
		this.restTemplate = restTemplate;
		this.id = id;
	}

	@Override
	protected User run() throws Exception {
		return restTemplate.getForObject("http://HELLO-SERVICE/users/{1}",
				User.class, id);
	}

	@Override
	protected User getFallback() {
		return new User();
	}

	@Override
	protected String getCacheKey() {
		return String.valueOf(id);
	}
}
