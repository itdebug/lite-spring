package com.itdebug.springframework.aop.framework.autoproxy;

import cn.hutool.core.bean.BeanException;
import com.itdebug.springframework.aop.AdvisedSupport;
import com.itdebug.springframework.aop.ClassFilter;
import com.itdebug.springframework.aop.PointCut;
import com.itdebug.springframework.aop.TargetSource;
import com.itdebug.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.itdebug.springframework.aop.framework.Advisor;
import com.itdebug.springframework.aop.framework.ProxyFactory;
import com.itdebug.springframework.beans.PropertyValues;
import com.itdebug.springframework.beans.factory.BeanFactory;
import com.itdebug.springframework.beans.factory.BeanFactoryAware;
import com.itdebug.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.itdebug.springframework.beans.factory.entity.BeanDefinition;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/23
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

	private DefaultListableBeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws SpringBeansException {
		this.beanFactory = (DefaultListableBeanFactory) beanFactory;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws SpringBeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws SpringBeansException {
		return bean;
	}

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws SpringBeansException {
		//避免死循环
		if (isInfrastructureClass(beanClass)) {
			return null;
		}

		Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
		try {
			for (AspectJExpressionPointcutAdvisor advisor : advisors) {
				ClassFilter classFilter = advisor.getPointcut().getClassFilter();
				if (classFilter.matches(beanClass)) {
					AdvisedSupport advisedSupport = new AdvisedSupport();
					BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
					Object bean = beanFactory.getInstantiationStrategy().instantiate(beanDefinition, beanName, null, null);
					TargetSource targetSource = new TargetSource(bean);
					advisedSupport.setTargetSource(targetSource);
					advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
					advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
					//返回代理对象
					return new ProxyFactory(advisedSupport).getProxy();
				}
			}
		} catch (Exception ex) {
			throw new SpringBeansException("Error create proxy bean for: " + beanName, ex);
		}
		return null;
	}

	private boolean isInfrastructureClass(Class<?> beanClass) {
		return Advice.class.isAssignableFrom(beanClass)
				|| PointCut.class.isAssignableFrom(beanClass)
				|| Advisor.class.isAssignableFrom(beanClass);
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws SpringBeansException {
		return true;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeanException {
		return pvs;
	}
}
