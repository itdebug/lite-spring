package com.itdebug.springframework.beans.factory.support;

import com.itdebug.springframework.beans.factory.FactoryBean;
import com.itdebug.springframework.beans.factory.config.BeanPostProcessor;
import com.itdebug.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.itdebug.springframework.beans.factory.entity.BeanDefinition;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.beans.factory.registry.DefaultSingletonBeanRegistry;
import com.itdebug.springframework.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /**
     * Cache of singleton objects created by FactoryBeans: FactoryBean name to object.
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>(16);

    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<StringValueResolver>();

    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName, null);
    }

    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        Object object = beanInstance;
        if (beanInstance instanceof FactoryBean) {
            FactoryBean factoryBean = (FactoryBean) beanInstance;
            try {
                if (factoryBean.isSingleton()) {
                    //singleton作用域bean，从缓存中获取
                    object = this.factoryBeanObjectCache.get(beanName);
                    if (object == null) {
                        object = factoryBean.getObject();
                        this.factoryBeanObjectCache.put(beanName, object);
                    } else {
                        //prototype作用域bean，新创建bean
                        object = factoryBean.getObject();
                    }
                }
            } catch (Exception e) {
                throw new SpringBeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
            }
        }
        return object;
    }

    @Override
    public Object getBean(String beanName, Object... args) throws SpringBeansException {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws SpringBeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String beanName, final Object[] args) {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            //如果是FactoryBean，从FactoryBean#getObject中创建bean
            return (T) getObjectForBeanInstance(bean, beanName);
        }
        BeanDefinition definition = getBeanDefinition(beanName);
        bean = createBean(beanName, definition, args);
        return (T) getObjectForBeanInstance(bean, beanName);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws SpringBeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws SpringBeansException;


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        //有则覆盖
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }


    public List<BeanPostProcessor> getBeanPostProcessorList() {
        return beanPostProcessorList;
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }
}
