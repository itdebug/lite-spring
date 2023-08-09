package com.itdebug.springframework.beans.factory.support;

import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.beans.factory.registry.BeanDefinitionRegistry;
import com.itdebug.springframework.core.io.Resource;
import com.itdebug.springframework.core.io.ResourceLoader;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/9
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws SpringBeansException;

    void loadBeanDefinitions(Resource... resources) throws SpringBeansException;

    void loadBeanDefinitions(String location) throws SpringBeansException;

}
