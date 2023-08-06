package com.itdebug.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工厂类
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    public Object getBean(String beanName) {
        return beanDefinitionMap.get(beanName).getBean();
    }

}
