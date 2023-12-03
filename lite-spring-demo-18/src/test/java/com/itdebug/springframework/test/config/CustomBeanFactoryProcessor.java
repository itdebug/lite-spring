package com.itdebug.springframework.test.config;

import com.itdebug.springframework.beans.PropertyValue;
import com.itdebug.springframework.beans.PropertyValues;
import com.itdebug.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.itdebug.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.itdebug.springframework.beans.factory.entity.BeanDefinition;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/12
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class CustomBeanFactoryProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws SpringBeansException {
		System.out.println("postProcessBeanFactory");
		BeanDefinition studentBeanDefinition = beanFactory.getBeanDefinition("teacher");
		PropertyValues propertyValues = studentBeanDefinition.getPropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("name", "我已经被修改为：Eric.Lu"));
	}
}
