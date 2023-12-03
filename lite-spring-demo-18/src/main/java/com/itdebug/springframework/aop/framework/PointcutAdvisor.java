package com.itdebug.springframework.aop.framework;

import com.itdebug.springframework.aop.PointCut;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/23
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface PointcutAdvisor extends Advisor {

	PointCut getPointcut();
}
