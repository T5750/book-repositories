package com.evangel.hystrix.command;

import org.springframework.web.client.RestTemplate;

import com.evangel.web.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

public class UserGetCommand extends HystrixCommand<User> {
	private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory
			.asKey("CommandKey");
	private RestTemplate restTemplate;
	private Long id;

	public UserGetCommand(RestTemplate restTemplate, Long id) {
		super(Setter
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet"))
				.andCommandKey(GETTER_KEY));
		this.restTemplate = restTemplate;
		this.id = id;
	}

	@Override
	protected User run() throws Exception {
		return restTemplate.getForObject("http://HELLO-SERVICE/users/{1}",
				User.class, id);
	}

	@Override
	protected String getCacheKey() {
		return String.valueOf(id);// 设置id置入缓存
	}

	public static void flushCahce(Long id) {
		// 刷新缓存，根据id进行清理
		HystrixRequestCache
				.getInstance(GETTER_KEY,
						HystrixConcurrencyStrategyDefault.getInstance())
				.clear(String.valueOf(id));
	}
}
