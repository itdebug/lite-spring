package com.itdebug.springframework.beans.factory.support;

import com.itdebug.springframework.beans.factory.BeanFactory;
import com.itdebug.springframework.beans.factory.config.BeanPostProcessor;
import com.itdebug.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.itdebug.springframework.beans.factory.entity.BeanDefinition;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.beans.factory.registry.DefaultSingletonBeanRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws SpringBeansException {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws SpringBeansException {
        return (T) getBean(name);
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

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition,
                                         Object[] args) throws SpringBeansException;


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        //有则覆盖
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }


    public List<BeanPostProcessor> getBeanPostProcessorList() {
        return beanPostProcessorList;
    }
}
