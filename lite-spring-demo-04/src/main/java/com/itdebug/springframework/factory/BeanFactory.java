package com.itdebug.springframework.factory;

import com.itdebug.springframework.factory.exception.SpringBeansException;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface BeanFactory {

    /**
     * 通过beanName获取Bean
     *
     * @param beanName
     * @return
     * @throws SpringBeansException
     */
    Object getBean(String beanName) throws SpringBeansException;

    /**
     * 通过
     *
     * @param beanName
     * @param args
     * @return
     * @throws SpringBeansException
     */
    Object getBean(String beanName, Object... args) throws SpringBeansException;
}
