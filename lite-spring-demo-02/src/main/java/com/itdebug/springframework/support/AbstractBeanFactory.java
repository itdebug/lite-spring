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
        Object bean = getSingleton(beanName);
        if (bean != null) {
            System.out.println("无需创建，从缓存获取");
            return bean;
        }
        BeanDefinition definition = getBeanDefinition(beanName);
        return createBean(beanName, definition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws SpringBeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws SpringBeansException;
}
