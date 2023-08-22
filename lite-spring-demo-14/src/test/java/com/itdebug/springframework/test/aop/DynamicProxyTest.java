package com.itdebug.springframework.test.aop;

import com.itdebug.springframework.aop.AdvisedSupport;
import com.itdebug.springframework.aop.MethodMatcher;
import com.itdebug.springframework.aop.TargetSource;
import com.itdebug.springframework.aop.aspectj.AspectJExpressionPointCut;
import com.itdebug.springframework.aop.framework.cglib.CglibAopProxy;
import com.itdebug.springframework.aop.framework.jdk.JdkDynamicAopProxy;
import com.itdebug.springframework.test.config.UserServiceInterceptor;
import com.itdebug.springframework.test.service.UserService;
import com.itdebug.springframework.test.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/15
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void setUp() throws Exception {
        UserService userService = new UserServiceImpl();

        advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(userService);

        UserServiceInterceptor methodInterceptor = new UserServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointCut("execution(* com.itdebug.springframework.test.service.UserService.location(..))").getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);
    }

    @Test
    public void testJdkDynamicProxy() throws Exception {
        UserService proxy = (UserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.location();
    }

    @Test
    public void testCglibDynamicProxy() throws Exception {
        UserService userService = (UserService) new CglibAopProxy(advisedSupport).getProxy();
        userService.location();
    }

}
