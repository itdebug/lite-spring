package com.itdebug.springframework.test.ioc;

import com.itdebug.springframework.context.support.ClassPathXmlApplicationContext;
import com.itdebug.springframework.test.bean.Car;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/11/26
 * @描述
 */
public class ValueAnnotationTest {

	@Test
	public void testValueAnnotation() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:value-annotation.xml");
		Car car = applicationContext.getBean("car", Car.class);
		assertThat(car.getBrand()).isEqualTo("Tesla");
	}
}
