package com.itdebug.springframework.support.instante;

import com.itdebug.springframework.entity.BeanDefinition;
import com.itdebug.springframework.exception.SpringBeansException;

import java.lang.reflect.Constructor;

public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws SpringBeansException {
//        Enhancer enhancer = new Enhancer();
        return null;
    }
}
