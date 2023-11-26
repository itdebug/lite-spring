package com.itdebug.springframework.aop.framework;

import com.itdebug.springframework.aop.AdvisedSupport;
import com.itdebug.springframework.aop.framework.cglib.CglibAopProxy;
import com.itdebug.springframework.aop.framework.jdk.JdkDynamicAopProxy;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/22
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class ProxyFactory {

	private AdvisedSupport advisedSupport;

	public ProxyFactory(AdvisedSupport advisedSupport) {
		this.advisedSupport = advisedSupport;
	}

	public Object getProxy() {
		return createAopProxy().getProxy();
	}

	private AopProxy createAopProxy() {
		if (advisedSupport.isProxyTargetClass()) {
			return new CglibAopProxy(advisedSupport);
		}

		return new JdkDynamicAopProxy(advisedSupport);
	}
}
