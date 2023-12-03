package com.itdebug.springframework.test.config;

import com.itdebug.springframework.aop.framework.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/22
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class UserServiceBeforeAdvise implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("BeforeAdvice: do something before get user location");
	}
}
