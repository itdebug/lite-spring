package com.itdebug.springframework.aop;

import java.lang.reflect.Method;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/15
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface MethodMatcher {

	boolean matches(Method method, Class<?> targetClass);
}
