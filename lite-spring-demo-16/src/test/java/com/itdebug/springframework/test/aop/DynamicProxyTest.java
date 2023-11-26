package com.itdebug.springframework.test.aop;

import com.itdebug.springframework.aop.AdvisedSupport;
import com.itdebug.springframework.aop.ClassFilter;
import com.itdebug.springframework.aop.MethodMatcher;
import com.itdebug.springframework.aop.TargetSource;
import com.itdebug.springframework.aop.aspectj.AspectJExpressionPointCut;
import com.itdebug.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.itdebug.springframework.aop.framework.ProxyFactory;
import com.itdebug.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.itdebug.springframework.aop.framework.cglib.CglibAopProxy;
import com.itdebug.springframework.aop.framework.jdk.JdkDynamicAopProxy;
import com.itdebug.springframework.test.config.UserServiceBeforeAdvise;
import com.itdebug.springframework.test.config.UserServiceInterceptor;
import com.itdebug.springframework.test.service.UserService;
import com.itdebug.springframework.test.service.UserServiceImpl;
import org.aopalliance.intercept.MethodInterceptor;
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

	@Test
	public void testProxyFactory() throws Exception {
		// 使用JDK动态代理
		advisedSupport.setProxyTargetClass(false);
		UserService proxy = (UserService) new ProxyFactory(advisedSupport).getProxy();
		proxy.location();

		// 使用CGLIB动态代理
		advisedSupport.setProxyTargetClass(true);
		proxy = (UserService) new ProxyFactory(advisedSupport).getProxy();
		proxy.location();
	}

	@Test
	public void testBeforeAdvice() throws Exception {
		//设置BeforeAdvice
		UserServiceBeforeAdvise beforeAdvice = new UserServiceBeforeAdvise();
		MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
		advisedSupport.setMethodInterceptor(methodInterceptor);

		UserService proxy = (UserService) new ProxyFactory(advisedSupport).getProxy();
		proxy.location();
	}

	@Test
	public void testAdvisor() throws Exception {
		UserService userService = new UserServiceImpl();

		//Advisor是Pointcut和Advice的组合
		String expression = "execution(* com.itdebug.springframework.test.service.UserService.location(..))";
		AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
		advisor.setExpression(expression);
		MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvise());
		advisor.setAdvice(methodInterceptor);

		ClassFilter classFilter = advisor.getPointcut().getClassFilter();
		if (classFilter.matches(userService.getClass())) {
			AdvisedSupport advisedSupport = new AdvisedSupport();

			TargetSource targetSource = new TargetSource(userService);
			advisedSupport.setTargetSource(targetSource);
			advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
			advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
			advisedSupport.setProxyTargetClass(true);   //JDK or CGLIB

			UserService proxy = (UserService) new ProxyFactory(advisedSupport).getProxy();
			proxy.location();
		}
	}
}
