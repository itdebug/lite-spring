package com.itdebug.springframework.beans.factory.config;

import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/12
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface BeanPostProcessor {

    /**
     * 在bean执行初始化方法之前执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws SpringBeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws SpringBeansException;


    Object postProcessAfterInitialization(Object bean, String beanName) throws SpringBeansException;
}
