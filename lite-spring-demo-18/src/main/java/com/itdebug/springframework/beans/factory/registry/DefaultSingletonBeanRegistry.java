package com.itdebug.springframework.beans.factory.registry;

import com.itdebug.springframework.beans.factory.DisposableBean;
import com.itdebug.springframework.beans.factory.ObjectFactory;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	//一级缓存
	private Map<String, Object> singletonObjects = new HashMap<>();

	//二级缓存
	private Map<String, Object> earlySingletonObjects = new HashMap<>();

	//三级缓存
	private Map<String, ObjectFactory<?>> singletonFactories = new HashMap<String, ObjectFactory<?>>();

	private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

	@Override
	public Object getSingleton(String beanName) {
		Object singletonObject = singletonObjects.get(beanName);
		if (singletonObject == null) {
			singletonObject = earlySingletonObjects.get(beanName);
			if (singletonObject == null) {
				ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
				if (singletonFactory != null) {
					singletonObject = singletonFactory.getObject();
					//从三级缓存放进二级缓存
					earlySingletonObjects.put(beanName, singletonObject);
					singletonFactories.remove(beanName);
				}
			}
		}
		return singletonObject;
	}

	@Override
	public void addSingleton(String beanName, Object singletonObject) {
		singletonObjects.put(beanName, singletonObject);
		earlySingletonObjects.remove(beanName);
		singletonFactories.remove(beanName);
	}

	protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
		singletonFactories.put(beanName, singletonFactory);
	}

	public void registerDisposableBean(String beanName, DisposableBean bean) {
		disposableBeans.put(beanName, bean);
	}

	public void destroySingletons() {
		Set<String> beanNames = disposableBeans.keySet();
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
