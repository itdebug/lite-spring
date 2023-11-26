package com.itdebug.springframework.test.service;

import com.itdebug.springframework.beans.factory.BeanFactory;
import com.itdebug.springframework.beans.factory.BeanFactoryAware;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.context.ApplicationContext;
import com.itdebug.springframework.context.ApplicationContextAware;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/12
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class SchoolService implements ApplicationContextAware, BeanFactoryAware {

	private ApplicationContext applicationContext;
	private BeanFactory beanFactory;

	public String getSchool() {
		System.out.println("getSchool");
		return "清华大学";
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws SpringBeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws SpringBeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}
}
