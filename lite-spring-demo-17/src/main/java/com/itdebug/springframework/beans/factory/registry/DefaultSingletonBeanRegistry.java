package com.itdebug.springframework.beans.factory.registry;

import com.itdebug.springframework.beans.factory.DisposableBean;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	private final Map<String, Object> singletonObjects = new HashMap<>();

	private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

	@Override
	public Object getSingleton(String name) {
		return singletonObjects.get(name);
	}

	public void addSingleton(String beanName, Object singletonObject) {
		singletonObjects.put(beanName, singletonObject);
	}

	public void registerDisposableBean(String beanName, DisposableBean bean) {
		disposableBeans.put(beanName, bean);
	}

	public void destroySingletons() {
		ArrayList<String> beanNames = new ArrayList<>(disposableBeans.keySet());
		for (String beanName : beanNames) {
			DisposableBean disposableBean = disposableBeans.remove(beanName);
			try {
				disposableBean.destroy();
			} catch (Exception e) {
				throw new SpringBeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
			}
		}
	}


}
