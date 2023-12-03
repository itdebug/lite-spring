package com.itdebug.springframework.test;

import com.itdebug.springframework.context.support.ClassPathXmlApplicationContext;
import com.itdebug.springframework.test.bean.Student;
import com.itdebug.springframework.test.service.SchoolService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/13
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class ScopeTest {

	@Test
	public void testPrototype() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype.xml");
		SchoolService bean1 = applicationContext.getBean("schoolService", SchoolService.class);
		SchoolService bean2 = applicationContext.getBean("schoolService", SchoolService.class);
		Assert.assertNotEquals(bean1, bean2);
	}

	@Test
	public void testSingleton() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype.xml");
		Student bean1 = applicationContext.getBean("student", Student.class);
		Student bean2 = applicationContext.getBean("student", Student.class);
		System.out.println(bean1);
		System.out.println(bean2);
		Assert.assertEquals(bean1, bean2);
	}
}
