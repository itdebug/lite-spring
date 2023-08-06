package com.itdebug.springframework;

/**
 * 定义 Bean 的配置元信息接口
 */
public class BeanDefinition {

    private final Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
