package com.itdebug.springframework.beans.factory.registry;

import com.itdebug.springframework.beans.factory.entity.BeanDefinition;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface BeanDefinitionRegistry {

	/**
	 * 注册Bean定义
	 *
	 * @param beanName
	 * @param beanDefinition
	 */
	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

	/**
	 * 获取Bean定义
	 *
	 * @param beanName
	 * @return
	 * @throws SpringBeansException
	 */
	BeanDefinition getBeanDefinition(String beanName) throws SpringBeansException;

	/**
	 * bean定义是否存在
	 *
	 * @param beanName
	 * @return
	 */
	boolean containsBeanDefinition(String beanName);

	String[] getBeanDefinitionNames();
}
