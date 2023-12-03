package com.itdebug.springframework.test;

import com.itdebug.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.itdebug.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.itdebug.springframework.context.support.ClassPathXmlApplicationContext;
import com.itdebug.springframework.test.bean.Student;
import com.itdebug.springframework.test.bean.Teacher;
import com.itdebug.springframework.test.config.CustomBeanFactoryProcessor;
import com.itdebug.springframework.test.config.CustomerBeanPostProcessor;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/12
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class ApplicationContextTest {

	@Test
	public void testInitAndDestroyMethod() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy.xml");
		applicationContext.registerShutdownHook();
	}

	@Test
	public void testClose() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy.xml");
		applicationContext.close();
	}

	@Test
	public void test_BeanFactoryPostProcessorAndBeanPostProcessor() {
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
		beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

		CustomBeanFactoryProcessor beanFactoryProcessor = new CustomBeanFactoryProcessor();
		beanFactoryProcessor.postProcessBeanFactory(defaultListableBeanFactory);

		CustomerBeanPostProcessor beanPostProcessor = new CustomerBeanPostProcessor();
		defaultListableBeanFactory.addBeanPostProcessor(beanPostProcessor);

		Teacher teacher = defaultListableBeanFactory.getBean("teacher", Teacher.class);
		System.out.println(teacher);

		assertThat(teacher.getName()).isEqualTo("我已经被修改为：Eric.Lu");

		Student student = defaultListableBeanFactory.getBean("student", Student.class);
		System.out.println(student);
		assertThat(student.getAge()).isEqualTo(100);
	}

	@Test
	public void testApplicationContext() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		Teacher teacher = applicationContext.getBean("teacher", Teacher.class);
		System.out.println(teacher);

		assertThat(teacher.getName()).isEqualTo("我已经被修改为：Eric.Lu");

		Student student = applicationContext.getBean("student", Student.class);
		System.out.println(student);
		assertThat(student.getAge()).isEqualTo(100);
	}
}
