package com.itdebug.springframework.test.expanding;

import com.itdebug.springframework.context.support.ClassPathXmlApplicationContext;
import com.itdebug.springframework.test.bean.Student;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/24
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class PropertyPlaceholderConfigurerTest {

	@Test
	public void test() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:property-placeholder-configurer.xml");

		Student student = applicationContext.getBean("student", Student.class);
		assertThat(student.getName()).isEqualTo("Mask");
	}
}
