package com.itdebug.springframework.test;

import com.itdebug.springframework.context.support.ClassPathXmlApplicationContext;
import com.itdebug.springframework.test.service.SchoolService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/13
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class AwareInterfaceTest {

	@Test
	public void test() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		SchoolService schoolService = applicationContext.getBean("schoolService", SchoolService.class);
		assertThat(schoolService.getApplicationContext()).isNotNull();
		assertThat(schoolService.getBeanFactory()).isNotNull();
	}
}
