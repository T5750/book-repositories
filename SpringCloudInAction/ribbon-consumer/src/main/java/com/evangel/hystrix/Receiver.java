package com.evangel.hystrix;

//接收者
public class Receiver {
	public void action() {
		// 正真的业务逻辑
		System.out.println(this.getClass().getName() + "正真的业务逻辑");
	}
}
