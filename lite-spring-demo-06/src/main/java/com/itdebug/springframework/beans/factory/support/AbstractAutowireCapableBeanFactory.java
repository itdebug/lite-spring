package com.itdebug.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.itdebug.springframework.beans.PropertyValue;
import com.itdebug.springframework.beans.PropertyValues;
import com.itdebug.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.itdebug.springframework.beans.factory.config.BeanPostProcessor;
import com.itdebug.springframework.beans.factory.entity.BeanDefinition;
import com.itdebug.springframework.beans.factory.entity.BeanReference;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.beans.factory.support.instante.CglibSubclassingInstantiationStrategy;
import com.itdebug.springframework.beans.factory.support.instante.InstantiationStrategy;

import java.lang.reflect.Constructor;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,
                                Object[] args) throws SpringBeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            //给 Bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            //执行Bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new SpringBeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    /**
     * 执行Bean的初始化方法
     *
     * @param name
     * @param bean
     * @param definition
     */
    private void invokeInitMethods(String name, Object bean, BeanDefinition definition) {


    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new SpringBeansException("Error setting property values：" + beanName);
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor realConstructor = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            if (null != args && constructor.getParameterTypes().length == args.length) {
                realConstructor = constructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, realConstructor, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,
                                                              String beanName) throws SpringBeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorList()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean,
                                                             String beanName) throws SpringBeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorList()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }


}
