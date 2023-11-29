package com.itdebug.springframework.beans.factory.registry;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述 获取单例的对象
 */
public interface SingletonBeanRegistry {

	Object getSingleton(String name);

	void addSingleton(String beanName, Object singletonObject);
}
