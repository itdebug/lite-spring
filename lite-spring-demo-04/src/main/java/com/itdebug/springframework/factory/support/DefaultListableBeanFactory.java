package com.itdebug.springframework.factory.support;

import com.itdebug.springframework.factory.entity.BeanDefinition;
import com.itdebug.springframework.factory.exception.SpringBeansException;
import com.itdebug.springframework.factory.registry.BeanDefinitionRegistry;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override protected BeanDefinition getBeanDefinition(String beanName) throws SpringBeansException {
        BeanDefinition definition = beanDefinitionMap.get(beanName);
        if (definition == null)
            throw new SpringBeansException("No Bean Named '" + beanName + "' is defined");
        return definition;
    }
}
