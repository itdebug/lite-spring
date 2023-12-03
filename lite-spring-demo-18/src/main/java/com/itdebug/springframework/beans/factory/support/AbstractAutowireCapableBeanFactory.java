package com.itdebug.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import com.itdebug.springframework.beans.PropertyValue;
import com.itdebug.springframework.beans.PropertyValues;
import com.itdebug.springframework.beans.factory.BeanFactoryAware;
import com.itdebug.springframework.beans.factory.DisposableBean;
import com.itdebug.springframework.beans.factory.InitializingBean;
import com.itdebug.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.itdebug.springframework.beans.factory.config.BeanPostProcessor;
import com.itdebug.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.itdebug.springframework.beans.factory.entity.BeanDefinition;
import com.itdebug.springframework.beans.factory.entity.BeanReference;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.beans.factory.support.instante.InstantiationStrategy;
import com.itdebug.springframework.beans.factory.support.instante.SimpleInstantiationStrategy;
import com.itdebug.springframework.core.convert.ConversionService;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

	private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

	@Override
	protected Object createBean(String beanName, BeanDefinition beanDefinition,
								Object[] args) throws SpringBeansException {

		//如果bean需要代理，则直接返回代理对象
		Object bean = resolveBeforeInstantiation(beanName, beanDefinition);
		if (bean != null) {
			return bean;
		}
		return doCreateBean(beanName, beanDefinition, args);

	}

	/**
	 * 执行InstantiationAwareBeanPostProcessor的方法，如果bean需要代理，直接返回代理对象
	 *
	 * @param beanName
	 * @param beanDefinition
	 * @return
	 */
	protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
		Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
		if (bean != null) {
			bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
		}
		return bean;
	}

	protected Object applyBeanPostProcessorsBeforeInstantiation(Class beanClass, String beanName) {
		for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorList()) {
			if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
				Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
				if (result != null) {
					return result;
				}
			}
		}

		return null;
	}

	protected Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
		Object bean = null;
		try {
			//bean实例化
			bean = createBeanInstance(beanDefinition, beanName, args);

			//为解决循环依赖问题，将实例化后的bean放进缓存中提前暴露
			if (beanDefinition.isSingleton()) {
				earlySingletonObjects.put(beanName, bean);
			}

			//实例化bean之后执行
			boolean continueWithPropertyPopulation = applyBeanPostProcessorsAfterInstantiation(beanName, bean);
			if (!continueWithPropertyPopulation) {
				return bean;
			}

			//在设置bean属性之前，允许BeanPostProcessor修改属性值
			applyBeanPostprocessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
			//给 Bean 填充属性
			applyPropertyValues(beanName, bean, beanDefinition);
			//执行Bean的初始化方法和BeanPostProcessor的前置和后置处理方法
			bean = initializeBean(beanName, bean, beanDefinition);
		} catch (Exception e) {
			throw new SpringBeansException("Instantiation of bean failed", e);
		}

		registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

		if (beanDefinition.isSingleton()) {
			addSingleton(beanName, bean);
		}
		return bean;
	}

	/**
	 * bean实例化后执行，如果返回false，不执行后续设置属性的逻辑
	 *
	 * @param beanName
	 * @param bean
	 * @return
	 */
	private boolean applyBeanPostProcessorsAfterInstantiation(String beanName, Object bean) {
		boolean continueWithPropertyPopulation = true;
		for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
			if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
				if (!((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessAfterInstantiation(bean, beanName)) {
					continueWithPropertyPopulation = false;
					break;
				}
			}
		}
		return continueWithPropertyPopulation;
	}

	/**
	 * 在设置bean属性之前，允许BeanPostProcessor修改属性值
	 *
	 * @param beanName
	 * @param bean
	 * @param beanDefinition
	 */
	protected void applyBeanPostprocessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
		for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorList()) {
			if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
				PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
				if (pvs != null) {
					for (PropertyValue propertyValue : pvs.getPropertyValues()) {
						beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
					}
				}
			}
		}
	}

	/**
	 * 注册有销毁方法的bean, 即bean继承自DisposableBean或有自定义的销毁方法
	 *
	 * @param beanName
	 * @param bean
	 * @param beanDefinition
	 */
	protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
		if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
			registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
		}
	}

	protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
		if (bean instanceof BeanFactoryAware) {
			((BeanFactoryAware) bean).setBeanFactory(this);
		}

		Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
		try {
			invokeInitMethods(beanName, wrappedBean, beanDefinition);
		} catch (Throwable e) {
			throw new SpringBeansException("Invocation of init method of bean[" + beanName + "] failed", e);
		}

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
	private void invokeInitMethods(String name, Object bean, BeanDefinition definition) throws Throwable {
		if (bean instanceof InitializingBean) {
			((InitializingBean) bean).afterPropertiesSet();
		}

		String initMethodName = definition.getInitMethodName();
		if (StrUtil.isNotEmpty(initMethodName)) {
			Method initMethod = ClassUtil.getPublicMethod(definition.getBeanClass(), initMethodName);
			if (initMethod == null) {
				throw new SpringBeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + name + "'");
			}
			initMethod.invoke(bean);
		}

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
				} else {
					//类型转换
					Class<?> sourceType = value.getClass();
					Class<?> targetType = (Class<?>) TypeUtil.getFieldType(bean.getClass(), name);
					ConversionService conversionService = getConversionService();
					if (conversionService != null) {
						if (conversionService.canConvert(sourceType, targetType)) {
							value = conversionService.convert(value, targetType);
						}
					}
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
