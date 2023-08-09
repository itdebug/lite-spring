package com.itdebug.springframework.beans.factory.support;

import com.itdebug.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.itdebug.springframework.beans.factory.entity.BeanDefinition;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.beans.factory.registry.BeanDefinitionRegistry;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override public BeanDefinition getBeanDefinition(String beanName) throws SpringBeansException {
        BeanDefinition definition = beanDefinitionMap.get(beanName);
        if (definition == null)
            throw new SpringBeansException("No Bean Named '" + beanName + "' is defined");
        return definition;
    }

    @Override public <T> Map<String, T> getBeansOfType(Class<T> type) throws SpringBeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return null;
    }

    @Override public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
