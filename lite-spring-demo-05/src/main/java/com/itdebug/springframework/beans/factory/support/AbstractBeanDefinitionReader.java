package com.itdebug.springframework.beans.factory.support;

import com.itdebug.springframework.beans.factory.registry.BeanDefinitionRegistry;
import com.itdebug.springframework.core.io.DefaultResourceLoader;
import com.itdebug.springframework.core.io.ResourceLoader;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/8
 * @地址 https://github.com/itdebug/
 * @描述
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
