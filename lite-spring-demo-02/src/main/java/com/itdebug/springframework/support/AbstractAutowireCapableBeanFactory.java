package com.itdebug.springframework.support;

import com.itdebug.springframework.entity.BeanDefinition;
import com.itdebug.springframework.exception.SpringBeansException;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override protected Object createBean(String beanName, BeanDefinition beanDefinition) throws SpringBeansException {
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (Exception e) {
            throw new SpringBeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
