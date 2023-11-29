package com.itdebug.springframework.beans.factory.config;

import com.itdebug.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

/**
 * 允许自定义修改BeanDefinition的属性值
 *
 * @创建人 Eric.Lu
 * @创建时间 2023/8/12
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface BeanFactoryPostProcessor {

	/**
	 * 在所有BeanDefinition加载完成后，但在bean实例化之前，提供修改BeanDefinition属性值的机制
	 *
	 * @param beanFactory
	 * @throws SpringBeansException
	 */
	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws SpringBeansException;
}
