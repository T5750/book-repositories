package com.evangel.hystrix.command;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.evangel.web.User;

import rx.Observable;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable Long id) {
		User user = null;
		// user = userService.getUserById(id);
		user = new UserCommand(restTemplate, 1L).execute();
		// final HystrixCommand.Setter setter = HystrixCommand.Setter
		// .withGroupKey(
		// HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
		// .andCommandKey(
		// HystrixCommandKey.Factory.asKey("ExampleCommand"));
		return user;
	}

	@RequestMapping(value = "/usersAsync/{id}", method = RequestMethod.GET)
	public User getUserByIdAsync(@PathVariable Long id) {
		Future<User> future = userService.getUserByIdAsync(id);
		User user = null;
		try {
			user = future.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@RequestMapping(value = "/usersObservable/{id}", method = RequestMethod.GET)
	public User getUserByIdObservable(@PathVariable Long id) {
		Observable<User> observable = userService.getUserByIdObservable(id);
		User user = null;
		try {// TODO
				// observable
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public User update() {
		return userService.update(new User());
	}


}