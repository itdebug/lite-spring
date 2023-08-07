package com.itdebug.springframework.factory.support.instante;

import com.itdebug.springframework.factory.entity.BeanDefinition;
import com.itdebug.springframework.factory.exception.SpringBeansException;
import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor,
        Object[] args) throws SpringBeansException;
}
