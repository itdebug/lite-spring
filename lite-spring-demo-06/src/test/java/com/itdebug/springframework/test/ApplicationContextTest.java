package com.itdebug.springframework.test;

import com.itdebug.springframework.context.support.ClassPathXmlApplicationContext;
import com.itdebug.springframework.test.bean.Student;
import com.itdebug.springframework.test.bean.Teacher;
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
