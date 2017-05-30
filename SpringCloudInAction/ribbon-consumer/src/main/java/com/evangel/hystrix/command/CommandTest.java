package com.evangel.hystrix.command;

import java.util.concurrent.Future;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

public class CommandTest {
	public static void main(String[] args) {
		HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory
				.asKey("ExampleGroup");
		HystrixCommand<String> hystrixCommand = new HystrixCommand<String>(
				groupKey) {
			@Override
			protected String run() throws Exception {
				return "hi";
			}
		};
		System.out.printf("exec command ... result = %s\n",
				hystrixCommand.execute());
		final HystrixCommand.Setter setter = HystrixCommand.Setter
				.withGroupKey(
						HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
				.andCommandKey(
						HystrixCommandKey.Factory.asKey("ExampleCommand"));
		HystrixCommand<String> hystrixCommand2 = new HystrixCommand<String>(
				setter) {
			@Override
			protected String run() throws Exception {
				return "hi setter";
			}
		};
		System.out.printf("exec command ... result = %s\n",
				hystrixCommand2.execute());
		// 异步执行
		HystrixCommand<String> hystrixCommandAsnyc = new HystrixCommand<String>(
				setter) {
			@Override
			protected String run() throws Exception {
				return "hi asnyc";
			}
		};
		Future<String> future = hystrixCommandAsnyc.queue();
		try {
			System.out.printf("exec command ... result = %s\n", future.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
