package com.itdebug.springframework.core.convert.support;

import com.itdebug.springframework.core.convert.converter.Converter;
import com.itdebug.springframework.core.convert.converter.ConverterFactory;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/11/28
 * @描述
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

	@Override
	public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
		return new StringToNumber<T>(targetType);
	}

	private static final class StringToNumber<T extends Number> implements Converter<String, T> {

		private final Class<T> targetType;

		public StringToNumber(Class<T> targetType) {
			this.targetType = targetType;
		}

		@Override
		public T convert(String source) {
			if (source.isEmpty()) {
				return null;
			}

			if (targetType.equals(Integer.class)) {
				return (T) Integer.valueOf(source);
			} else if (targetType.equals(Long.class)) {
				return (T) Long.valueOf(source);
			} else if (targetType.equals(Float.class)) {
				return (T) Float.valueOf(source);
			}
			//TODO 其他数字类型

			else {
				throw new IllegalArgumentException(
						"Cannot convert String [" + source + "] to target class [" + targetType.getName() + "]");
			}
		}
	}
}
