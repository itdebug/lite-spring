package com.itdebug.springframework.test.common.test;

import com.itdebug.springframework.context.support.ClassPathXmlApplicationContext;
import com.itdebug.springframework.core.convert.converter.Converter;
import com.itdebug.springframework.core.convert.support.GenericConversionService;
import com.itdebug.springframework.core.convert.support.StringToNumberConverterFactory;
import com.itdebug.springframework.test.bean.Car;
import com.itdebug.springframework.test.common.StringToBooleanConverter;
import com.itdebug.springframework.test.common.StringToIntegerConverter;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/11/28
 * @描述
 */
public class TypeConversionTest {

	/**
	 * 单个类型转换，遇到一个写一个实现，很浪费
	 *
	 * @throws Exception
	 */
	@Test
	public void testStringToIntegerConverter() throws Exception {
		StringToIntegerConverter converter = new StringToIntegerConverter();
		Integer num = converter.convert("8888");
		assertThat(num).isEqualTo(8888);
	}

	@Test
	public void testStringToNumberConverterFactory() throws Exception {
		StringToNumberConverterFactory converterFactory = new StringToNumberConverterFactory();

		Converter<String, Integer> stringToIntegerConverter = converterFactory.getConverter(Integer.class);
		Integer intNum = stringToIntegerConverter.convert("8888");
		assertThat(intNum).isEqualTo(8888);

		Converter<String, Long> stringToLongConverter = converterFactory.getConverter(Long.class);
		Long longNum = stringToLongConverter.convert("8888");
		assertThat(longNum).isEqualTo(8888L);
	}

	@Test
	public void testGenericConverter() throws Exception {
		StringToBooleanConverter converter = new StringToBooleanConverter();
		Boolean flag = (Boolean) converter.convert("true", String.class, Boolean.class);
		assertThat(flag).isTrue();
	}

	@Test
	public void testGenericConversionService() throws Exception {
		GenericConversionService conversionService = new GenericConversionService();
		conversionService.addConverter(new StringToIntegerConverter());

		conversionService.addConverterFactory(new StringToNumberConverterFactory());
		assertThat(conversionService.canConvert(String.class, Long.class)).isTrue();

		Long longNum = conversionService.convert("8888", Long.class);
		assertThat(longNum).isEqualTo(8888L);

		Integer intNum = conversionService.convert("8888", Integer.class);
		assertThat(conversionService.canConvert(String.class, Integer.class)).isTrue();
		assertThat(intNum).isEqualTo(8888);

		conversionService.addConverter(new StringToBooleanConverter());
		assertThat(conversionService.canConvert(String.class, Boolean.class)).isTrue();
		Boolean flag = conversionService.convert("true", Boolean.class);
		assertThat(flag).isTrue();
	}

	@Test
	public void testConversionService() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:type-conversion-second-part.xml");

		Car car = applicationContext.getBean("car", Car.class);
		assertThat(car.getPrice()).isEqualTo(1000000);
		assertThat(car.getProduceDate()).isEqualTo(LocalDate.of(2021, 1, 1));
	}
}
