package com.itdebug.springframework.beans.factory.config;

import com.itdebug.springframework.beans.factory.BeanFactory;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/8
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

	/**
	 * 执行BeanPostProcessors的postBeanBeforeInitialization方法
	 *
	 * @param existingBean
	 * @param beanName
	 * @return
	 * @throws SpringBeansException
	 */
	Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws SpringBeansException;

	/**
	 * 执行BeanPostProcessors的postProcessAfterInitialization方法
	 *
	 * @param existingBean
	 * @param beanName
	 * @return
	 * @throws SpringBeansException
	 */
	Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws SpringBeansException;
}
