package com.itdebug.springframework.core.convert;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/11/29
 * @描述
 */
public interface ConversionService {

	boolean canConvert(Class<?> sourceType, Class<?> targetType);

	<T> T convert(Object source, Class<T> targetType);

}
