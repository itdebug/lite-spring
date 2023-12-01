package com.itdebug.springframework.core.convert.support;

import com.itdebug.springframework.core.convert.converter.ConverterRegistry;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/12/1
 * @描述
 */
public class DefaultConversionService extends GenericConversionService {

	public DefaultConversionService() {
		addDefaultConverters(this);
	}

	public static void addDefaultConverters(ConverterRegistry converterRegistry) {
		converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
		//TODO 添加其他ConverterFactory
	}
}
