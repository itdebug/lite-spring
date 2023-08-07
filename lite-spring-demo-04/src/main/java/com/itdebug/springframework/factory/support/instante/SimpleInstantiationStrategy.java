package com.itdebug.springframework.factory.support.instante;

import com.itdebug.springframework.factory.entity.BeanDefinition;
import com.itdebug.springframework.factory.exception.SpringBeansException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor,
        Object[] args) throws SpringBeansException {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (null != constructor) {
                return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new SpringBeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
