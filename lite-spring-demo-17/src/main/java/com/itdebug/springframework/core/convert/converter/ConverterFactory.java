package com.itdebug.springframework.core.convert.converter;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/11/28
 * @描述
 */
public interface ConverterFactory<S, R> {

	<T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
