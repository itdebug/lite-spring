package com.itdebug.springframework.context.support;

import com.itdebug.springframework.beans.factory.config.BeanPostProcessor;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.context.ApplicationContext;
import com.itdebug.springframework.context.ApplicationContextAware;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/13
 * @地址 https://github.com/itdebug/
 * @描述 bean设置应用上下文的真正处理类
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws SpringBeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws SpringBeansException {
        return bean;
    }
}
