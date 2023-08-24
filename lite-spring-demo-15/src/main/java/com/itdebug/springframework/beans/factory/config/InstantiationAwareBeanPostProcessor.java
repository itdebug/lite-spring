package com.itdebug.springframework.beans.factory.config;

import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/23
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    /**
     * 在bean实例化之前执行
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws SpringBeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws SpringBeansException;

}
