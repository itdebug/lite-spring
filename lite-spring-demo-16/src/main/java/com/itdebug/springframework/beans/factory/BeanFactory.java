package com.itdebug.springframework.beans.factory;

import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

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
     * 指定参数获取对应的Bean
     *
     * @param beanName
     * @param args
     * @return
     * @throws SpringBeansException
     */
    Object getBean(String beanName, Object... args) throws SpringBeansException;

    /**
     * 通过类型获取Bean
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws SpringBeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws SpringBeansException;

    <T> T getBean(Class<T> requiredType) throws SpringBeansException;
}
