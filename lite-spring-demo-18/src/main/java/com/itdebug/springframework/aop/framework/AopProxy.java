package com.itdebug.springframework.aop.framework;

/**
 * AopProxy是获取代理对象的抽象接口
 *
 * @创建人 Eric.Lu
 * @创建时间 2023/8/16
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface AopProxy {

	Object getProxy();
}
