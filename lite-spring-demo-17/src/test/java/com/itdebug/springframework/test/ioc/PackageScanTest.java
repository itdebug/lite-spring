package com.itdebug.springframework.test.ioc;

import com.itdebug.springframework.context.support.ClassPathXmlApplicationContext;
import com.itdebug.springframework.test.bean.Student;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/24
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class PackageScanTest {

	@Test
	public void testScanPackage() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:package-scan.xml");

		Student student = applicationContext.getBean("student", Student.class);
		assertThat(student).isNotNull();
	}
}
