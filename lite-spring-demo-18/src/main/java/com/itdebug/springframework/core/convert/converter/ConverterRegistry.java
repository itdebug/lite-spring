package com.itdebug.springframework.core.convert.converter;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/11/29
 * @描述
 */
public interface ConverterRegistry {

	void addConverter(Converter<?, ?> converter);

	void addConverterFactory(ConverterFactory<?, ?> converterFactory);

	void addConverter(GenericConverter converter);
}
