package com.evangel.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable Long id) {
		return new User("evangel", 99);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User getUser() {
		return new User("evangel", 999);
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public List<User> findAll(@RequestParam List<Long> ids) {
		List<User> userList = new ArrayList<>();
		userList.add(new User("evangel", 99));
		userList.add(new User("evangel", 999));
		return userList;
	}
}
