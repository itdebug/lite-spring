package com.itdebug.springframework.context;

import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

/**
 * SPI interface to be implemented by most if not all application contexts.
 * Provides facilities to configure an application context in addition
 * to the application context client methods in the
 * {@link org.springframework.context.ApplicationContext} interface.
 *
 * <p>Configuration and lifecycle methods are encapsulated here to avoid
 * making them obvious to ApplicationContext client code. The present
 * methods should only be used by startup and shutdown code.
 * <p>
 * 配置生命周期的方法
 *
 * @创建人 Eric.Lu
 * @创建时间 2023/8/12
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

	/**
	 * 刷新容器
	 *
	 * @throws SpringBeansException
	 */
	void refresh() throws SpringBeansException;

	/**
	 * 关闭应用上下文
	 */
	void close();

	/**
	 * 向虚拟机中注册一个钩子方法，在虚拟机关闭之前执行关闭容器等操作
	 */
	void registerShutdownHook();
}
