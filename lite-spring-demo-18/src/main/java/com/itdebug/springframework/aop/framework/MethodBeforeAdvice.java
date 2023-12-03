package com.itdebug.springframework.aop.framework;

import java.lang.reflect.Method;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/22
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

	void before(Method method, Object[] args, Object target) throws Throwable;

}
