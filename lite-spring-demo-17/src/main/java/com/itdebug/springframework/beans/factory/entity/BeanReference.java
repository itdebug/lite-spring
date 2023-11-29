package com.itdebug.springframework.beans.factory.entity;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/7
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class BeanReference {

	private final String beanName;

	public BeanReference(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}
}
