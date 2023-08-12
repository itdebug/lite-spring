package com.itdebug.springframework.test.config;

import com.itdebug.springframework.beans.factory.config.BeanPostProcessor;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.test.bean.Student;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/12
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class CustomerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws SpringBeansException {
        System.out.println("Bean初始化前置处理器");
        if ("student".equals(beanName)) {
            ((Student) bean).setAge(100);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws SpringBeansException {
        System.out.println("Bean初始化后置处理器");
        return bean;
    }
}
