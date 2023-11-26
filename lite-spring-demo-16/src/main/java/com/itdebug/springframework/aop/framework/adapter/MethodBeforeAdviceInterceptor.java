package com.itdebug.springframework.aop.framework.adapter;


import com.itdebug.springframework.aop.framework.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/23
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

	private MethodBeforeAdvice advice;

	public MethodBeforeAdviceInterceptor() {
	}

	public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
		this.advice = advice;
	}

	public void setAdvice(MethodBeforeAdvice advice) {
		this.advice = advice;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		//在执行被代理方法之前，先执行before advice操作
		this.advice.before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
		return invocation.proceed();
	}
}
