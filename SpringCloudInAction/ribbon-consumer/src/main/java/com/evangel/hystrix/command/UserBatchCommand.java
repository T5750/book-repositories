package com.evangel.hystrix.command;

import java.util.List;

import com.evangel.web.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

public class UserBatchCommand extends HystrixCommand<List<User>> {
	UserService userService;
	List<Long> userIds;

	public UserBatchCommand(UserService userService, List<Long> userIds) {
		super(Setter
				.withGroupKey(HystrixCommandGroupKey.Factory
						.asKey("userServiceGroup"))
				.andCommandKey(
						HystrixCommandKey.Factory.asKey("userServiceCommand")));
		this.userService = userService;
		this.userIds = userIds;
	}

	@Override
	protected List<User> run() throws Exception {
		return userService.findAll(userIds);
	}
}
