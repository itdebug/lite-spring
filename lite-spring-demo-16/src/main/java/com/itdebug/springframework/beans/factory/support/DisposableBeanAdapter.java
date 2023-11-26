package com.itdebug.springframework.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.itdebug.springframework.beans.factory.DisposableBean;
import com.itdebug.springframework.beans.factory.entity.BeanDefinition;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

import java.lang.reflect.Method;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/13
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class DisposableBeanAdapter implements DisposableBean {

	private final Object bean;
	private final String beanName;

	private final String destoryMethodName;

	public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
		this.bean = bean;
		this.beanName = beanName;
		this.destoryMethodName = beanDefinition.getDestroyMethodName();
	}

	/**
	 * 适配器模式
	 * 可以在销毁方法中调用 disposableBean的destroy 和 自定义的destroy
	 *
	 * @throws Exception
	 */
	@Override
	public void destroy() throws Exception {
		if (bean instanceof DisposableBean) {
			((DisposableBean) bean).destroy();
		}

		if (StrUtil.isNotEmpty(destoryMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destoryMethodName))) {
			Method destoryMethod = ClassUtil.getPublicMethod(bean.getClass(), destoryMethodName);
			if (destoryMethod == null) {

				throw new SpringBeansException("Couldn't find a destroy method named '" + destoryMethodName + "' on bean with name '" + beanName + "'");
			}
			destoryMethod.invoke(bean);
		}
	}
}
