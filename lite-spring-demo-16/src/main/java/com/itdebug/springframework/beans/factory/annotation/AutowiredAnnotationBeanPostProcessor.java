package com.itdebug.springframework.beans.factory.annotation;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.bean.BeanUtil;
import com.itdebug.springframework.beans.PropertyValues;
import com.itdebug.springframework.beans.factory.BeanFactory;
import com.itdebug.springframework.beans.factory.BeanFactoryAware;
import com.itdebug.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.itdebug.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

import java.lang.reflect.Field;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/11/26
 * @描述
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

	private ConfigurableListableBeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws SpringBeansException {
		this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeanException {
		//处理@Value注解
		Class<?> clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Value valueAnnotation = field.getAnnotation(Value.class);
			if (valueAnnotation != null) {
				String value = valueAnnotation.value();
				value = beanFactory.resolveEmbeddedValue(value);
				BeanUtil.setFieldValue(bean, field.getName(), value);
			}
		}

		//处理@Autowired注解（下一节实现）
		return pvs;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws SpringBeansException {
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws SpringBeansException {
		return null;
	}

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws SpringBeansException {
		return null;
	}
}
