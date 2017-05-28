package com.evangel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
	public String helloConsumer() {
		return restTemplate
				.getForEntity("http://HELLO-SERVICE/hello", String.class)
				.getBody();
	}

	@Autowired
	HelloService helloService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String helloServiceConsumer() {
		return helloService.helloService();
	}

	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public String helloServiceConsumer2() {
		return helloService.hello();
	}
}