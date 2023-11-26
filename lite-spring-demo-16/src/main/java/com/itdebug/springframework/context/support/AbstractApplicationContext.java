package com.itdebug.springframework.context.support;

import com.itdebug.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.itdebug.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.itdebug.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.itdebug.springframework.beans.factory.config.BeanPostProcessor;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.context.ApplicationEvent;
import com.itdebug.springframework.context.ApplicationListener;
import com.itdebug.springframework.context.ConfigurableApplicationContext;
import com.itdebug.springframework.context.event.ApplicationEventMulticaster;
import com.itdebug.springframework.context.event.ContextClosedEvent;
import com.itdebug.springframework.context.event.ContextRefreshedEvent;
import com.itdebug.springframework.context.event.SimpleApplicationEventMulticaster;
import com.itdebug.springframework.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/12
 * @地址 https://github.com/itdebug/
 * @描述 抽象应用上下文
 * <p>
 * 模板方法：抽象类+抽象方法+普通方法
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws SpringBeansException {
        //创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //添加ApplicationContextAwareProcessor，让继承自ApplicationContextAware的bean能感知bean
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //在实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        //BeanPostProcessor 需要提前与其它bean实例化之前注册
        registerBeanPostProcessor(beanFactory);

        //初始化事件发布者
        initApplicationEventMulticaster();

        //注册事件监听器
        registerListeners();

        //提前实例化单例bean
        beanFactory.preInstantiateSingletons();

        //发布容器刷新完成事件
        finishRefresh();
    }

    /**
     * 发布容器刷新完成事件
     */
    protected void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    protected void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        beanPostProcessorMap.values().forEach(beanFactory::addBeanPostProcessor);
    }

    protected void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        beanFactoryPostProcessorMap.values().forEach(processor -> processor.postProcessBeanFactory(beanFactory));
    }

    protected abstract void refreshBeanFactory() throws SpringBeansException;

    public abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public Object getBean(String beanName) throws SpringBeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws SpringBeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws SpringBeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws SpringBeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void close() {
        doClose();
    }

    @Override
    public void registerShutdownHook() {
        Thread shutdownHook = new Thread() {
            @Override
            public void run() {
                doClose();
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    protected void doClose() {
        publishEvent(new ContextClosedEvent(this));
        destroyBeans();
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }


}
