package com.itdebug.springframework.test.config;

import com.itdebug.springframework.beans.factory.FactoryBean;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import com.itdebug.springframework.test.bean.Teacher;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/13
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class TeacherFactoryBean implements FactoryBean<Teacher> {

	private String name;

	@Override
	public Teacher getObject() throws SpringBeansException {
		Teacher teacher = new Teacher();
		teacher.setName(name);
		return teacher;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public String getName() {
		return name;
	}
}
