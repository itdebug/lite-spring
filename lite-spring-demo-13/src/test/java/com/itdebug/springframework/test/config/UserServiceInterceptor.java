package com.itdebug.springframework.test.config;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/16
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class UserServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Do something before the location");
        Object result = methodInvocation.proceed();
        System.out.println("Do something before the location");
        return result;
    }
}
