package com.itdebug.springframework.context.support;

import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.itdebug.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/12
 * @地址 https://github.com/itdebug/
 * @描述
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

	@Override
	protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws SpringBeansException {
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
		String[] configLocations = getConfigLocations();
		if (configLocations != null) {
			beanDefinitionReader.loadBeanDefinitions(configLocations);
		}
	}

	protected abstract String[] getConfigLocations();
}
