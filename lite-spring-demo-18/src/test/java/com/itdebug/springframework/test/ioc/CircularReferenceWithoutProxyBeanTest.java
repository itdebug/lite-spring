package com.itdebug.springframework.test.ioc;

import com.itdebug.springframework.context.support.ClassPathXmlApplicationContext;
import com.itdebug.springframework.test.bean.circle.A;
import com.itdebug.springframework.test.bean.circle.B;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/12/3
 * @描述
 */
public class CircularReferenceWithoutProxyBeanTest {

	@Test
	public void testCircularReference() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular-reference-without-proxy-bean.xml");
		A a = applicationContext.getBean("a", A.class);
		B b = applicationContext.getBean("b", B.class);
		assertThat(a.getB() == b).isTrue();
	}
}
