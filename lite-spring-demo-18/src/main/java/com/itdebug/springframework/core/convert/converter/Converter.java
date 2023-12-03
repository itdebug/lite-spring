package com.itdebug.springframework.core.convert.converter;

/**
 * 类型转换抽象接口
 *
 * @创建人 Eric.Lu
 * @创建时间 2023/11/28
 * @描述
 */
public interface Converter<S, T> {

	T convert(S source);
}
