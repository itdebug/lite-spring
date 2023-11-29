package com.itdebug.springframework.test.common;

import com.itdebug.springframework.core.convert.converter.Converter;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/11/28
 * @描述
 */
public class StringToIntegerConverter implements Converter<String
		, Integer> {

	@Override
	public Integer convert(String source) {
		return Integer.valueOf(source);
	}
}
