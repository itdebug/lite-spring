package com.itdebug.springframework.test.common;

import com.itdebug.springframework.beans.factory.FactoryBean;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

import java.util.HashSet;
import java.util.Set;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/12/1
 * @描述
 */
public class ConvertersFactoryBean implements FactoryBean<Set<?>> {

	@Override
	public Set<?> getObject() throws SpringBeansException {
		HashSet<Object> converters = new HashSet<>();
		StringToLocalDateConverter stringToLocalDateConverter = new StringToLocalDateConverter("yyyy-MM-dd");
		converters.add(stringToLocalDateConverter);
		return converters;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
