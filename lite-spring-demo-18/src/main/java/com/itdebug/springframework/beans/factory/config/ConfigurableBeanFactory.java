package com.itdebug.springframework.beans.factory.config;

import com.itdebug.springframework.beans.factory.registry.SingletonBeanRegistry;
import com.itdebug.springframework.core.convert.ConversionService;
import com.itdebug.springframework.util.StringValueResolver;

/**
 * 可配置Bean 单例 OR 原型
 *
 * @创建人 Eric.Lu
 * @创建时间 2023/8/8
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

	String SCOPE_SINGLETON = "singleton";
	String SCOPE_PROTOTYPE = "prototype";

	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

	/**
	 * 销毁单例bean
	 */
	void destroySingletons();

	void addEmbeddedValueResolver(StringValueResolver valueResolver);

	String resolveEmbeddedValue(String value);

	void setConversionService(ConversionService conversionService);

	ConversionService getConversionService();
}
