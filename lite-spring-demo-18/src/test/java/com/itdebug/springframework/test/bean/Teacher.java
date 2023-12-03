package com.itdebug.springframework.test.bean;

import com.itdebug.springframework.beans.factory.DisposableBean;
import com.itdebug.springframework.beans.factory.InitializingBean;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/12
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class Teacher implements InitializingBean, DisposableBean {

	private String name;

	private Student student;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"name='" + name + '\'' +
				", student=" + student +
				'}';
	}

	public void customInitMethod() {
		System.out.println("customInitMethod start");
	}

	public void customerDestroyMethod() {
		System.out.println("customerDestroyMethod start");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("调用 destroy 方法成功");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("调用 afterPropertiesSet 方法成功");
	}
}
