package com.itdebug.springframework.beans.factory.annotation;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.TypeUtil;
import com.itdebug.springframework.beans.PropertyValues;
import com.itdebug.springframework.beans.factory.BeanFactory;
import com.itdebug.springframework.beans.factory.BeanFactoryAware;
import com.itdebug.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.itdebug.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.core.convert.ConversionService;

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
				Object value = valueAnnotation.value();
				value = beanFactory.resolveEmbeddedValue((String) value);

				//类型转换
				Class<?> sourceType = value.getClass();
				Class<?> targetType = (Class<?>) TypeUtil.getType(field);
				ConversionService conversionService = beanFactory.getConversionService();
				if (conversionService != null) {
					if (conversionService.canConvert(sourceType, targetType)) {
						value = conversionService.convert(value, targetType);
					}
				}
				BeanUtil.setFieldValue(bean, field.getName(), value);
			}
		}

		//处理@Autowired注解（下一节实现）
		for (Field field : fields) {
			Autowired autowired = field.getAnnotation(Autowired.class);
			if (autowired != null) {
				Class<?> fieldType = field.getType();
				Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
				Object dependentBean = null;
				if (qualifierAnnotation != null) {
					String dependentBeanName = qualifierAnnotation.value();
					dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
				} else {
					dependentBean = beanFactory.getBean(fieldType);
				}
				BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
			}
		}
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
