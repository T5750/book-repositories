package com.evangel.hystrix.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.evangel.web.User;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCommand;

public class UserCollapserCommand
		extends HystrixCollapser<List<User>, User, Long> {
	private UserService userService;
	private Long userId;

	public UserCollapserCommand(UserService userService, Long userId) {
		super(Setter.withCollapserKey(
				HystrixCollapserKey.Factory.asKey("userServiceCommand")));
		this.userService = userService;
		this.userId = userId;
	}

	@Override
	public Long getRequestArgument() {
		return userId;
	}

	@Override
	protected HystrixCommand<List<User>> createCommand(
			Collection<CollapsedRequest<User, Long>> collapsedRequests) {
		List<Long> userIds = new ArrayList<>(collapsedRequests.size());
		userIds.addAll(
				collapsedRequests.stream().map(CollapsedRequest::getArgument)
						.collect(Collectors.toList()));
		return new UserBatchCommand(userService, userIds);
	}

	@Override
	protected void mapResponseToRequests(List<User> batchResponse,
			Collection<CollapsedRequest<User, Long>> collapsedRequests) {
		int count = 0;
		for (CollapsedRequest<User, Long> collapsedRequest : collapsedRequests) {
			User user = batchResponse.get(count++);
			collapsedRequest.setResponse(user);
		}
	}
}
