package com.itdebug.springframework.aop;


import org.aopalliance.intercept.MethodInterceptor;

/**
 * TargetSource，被代理对象的封装。
 * MethodInterceptor，方法拦截器，是AOP Alliance的"公民"，
 * 顾名思义，可以拦截方法，可在被代理执行的方法前后增加代理行为。
 *
 * @创建人 Eric.Lu
 * @创建时间 2023/8/15
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class AdvisedSupport {

    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
