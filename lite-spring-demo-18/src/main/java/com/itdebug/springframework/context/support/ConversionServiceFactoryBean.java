package com.itdebug.springframework.context.support;

import com.itdebug.springframework.beans.factory.FactoryBean;
import com.itdebug.springframework.beans.factory.InitializingBean;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.core.convert.ConversionService;
import com.itdebug.springframework.core.convert.converter.Converter;
import com.itdebug.springframework.core.convert.converter.ConverterFactory;
import com.itdebug.springframework.core.convert.converter.ConverterRegistry;
import com.itdebug.springframework.core.convert.converter.GenericConverter;
import com.itdebug.springframework.core.convert.support.DefaultConversionService;
import com.itdebug.springframework.core.convert.support.GenericConversionService;

import java.util.Set;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/12/1
 * @描述
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

	private Set<?> converters;

	private GenericConversionService conversionService;

	@Override
	public ConversionService getObject() throws SpringBeansException {
		return conversionService;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		conversionService = new DefaultConversionService();
		registerConverters(converters, conversionService);
	}

	private void registerConverters(Set<?> converters, ConverterRegistry registry) {
		if (converters != null) {
			for (Object converter : converters) {
				if (converter instanceof GenericConverter) {
					registry.addConverter((GenericConverter) converter);
				} else if (converter instanceof Converter<?, ?>) {
					registry.addConverter((Converter<?, ?>) converter);
				} else if (converter instanceof ConverterFactory<?, ?>) {
					registry.addConverterFactory((ConverterFactory<?, ?>) converter);
				} else {
					throw new IllegalArgumentException("Each converter object must implement one of the " +
							"Converter, ConverterFactory, or GenericConverter interfaces");
				}
			}
		}
	}

	public void setConverters(Set<?> converters) {
		this.converters = converters;
	}
}
