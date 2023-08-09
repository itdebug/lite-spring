package com.itdebug.springframework.beans.factory;

import com.itdebug.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.itdebug.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.itdebug.springframework.beans.factory.entity.BeanDefinition;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/8
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws SpringBeansException;

}
