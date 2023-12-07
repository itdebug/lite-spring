package com.itdebug.springframework.test.common;

import com.itdebug.springframework.aop.framework.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/12/6
 * @描述
 */
public class ABeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {

	}
}
