package com.itdebug.springframework.test;

import com.itdebug.springframework.context.support.ClassPathXmlApplicationContext;
import com.itdebug.springframework.test.bean.Teacher;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/13
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class FactoryBeanTest {

	@Test
	public void testFactoryBean() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");

		Teacher car = applicationContext.getBean("teacher", Teacher.class);
		assertThat(car.getName()).isEqualTo("panda");
	}
}
