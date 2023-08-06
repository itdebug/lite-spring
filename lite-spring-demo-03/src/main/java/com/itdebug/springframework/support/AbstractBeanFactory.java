package com.itdebug.springframework.support;

import com.itdebug.springframework.BeanFactory;
import com.itdebug.springframework.entity.BeanDefinition;
import com.itdebug.springframework.exception.SpringBeansException;
import com.itdebug.springframework.registry.DefaultSingletonBeanRegistry;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws SpringBeansException {
        return doGetBean(beanName, args);
    }

    protected <T> T doGetBean(final String beanName, final Object[] args) {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            System.out.println("无需创建，从缓存获取");
            return (T) bean;
        }
        BeanDefinition definition = getBeanDefinition(beanName);
        return (T) createBean(beanName, definition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws SpringBeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws SpringBeansException;
}
