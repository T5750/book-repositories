package com.evangel.hystrix.command;

import java.util.List;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.evangel.web.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

import rx.Observable;
import rx.Subscriber;

@Service
public class UserService {
	@Autowired
	private RestTemplate restTemplate;

	@CacheResult // (cacheKeyMethod = "getUserByIdCacheKey")
	@HystrixCommand(fallbackMethod = "defaultUser")
	public User getUserById(Long id) {// @CacheKey("id")
		return restTemplate.getForObject("http://HELLO-SERVICE/users/{1}",
				User.class, id);
	}

	@HystrixCommand
	public Future<User> getUserByIdAsync(final Long id) {
		return new AsyncResult<User>() {
			public User invoke() {
				return restTemplate.getForObject(
						"http://HELLO-SERVICE/users/{1}", User.class, id);
			}
		};
	}

	@HystrixCommand
	protected Observable<User> getUserByIdObservable(final Long id) {
		return Observable.create(new Observable.OnSubscribe<User>() {
			@Override
			public void call(Subscriber<? super User> observer) {
				try {
					if (!observer.isUnsubscribed()) {
						User user = restTemplate.getForObject(
								"http://HELLO-SERVICE/users/{1}", User.class,
								id);
						observer.onNext(user);
						observer.onCompleted();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@HystrixCommand(fallbackMethod = "defaultUserSec")
	public User defaultUser(Long id) {
		return new User("First Fallback");
	}

	public User defaultUserSec(Long id) {
		return new User("Second Fallback");
	}

	private String getUserByIdCacheKey(Long id) {
		return String.valueOf(id);
	}

	// @CacheRemove(commandKey = "getUserById")
	@HystrixCommand
	public User update(User user) {
		return restTemplate.postForObject("http://HELLO-SERVICE/users", user,
				User.class);
	}

	@HystrixCollapser(batchMethod = "findAll", collapserProperties = {
			@HystrixProperty(name = "timerDelayInMilliseconds", value = "100") })
	public User find(Long id) {
		return null;
	}

	@HystrixCommand
	public List<User> findAll(List<Long> ids) {
		return restTemplate.getForObject("http://HELLO-SERVICE/users?ids={1}",
				List.class, StringUtils.join(ids, ","));
	}
}
