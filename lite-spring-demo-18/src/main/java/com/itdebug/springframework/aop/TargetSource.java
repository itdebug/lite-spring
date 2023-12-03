package com.itdebug.springframework.aop;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/15
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class TargetSource {

	private final Object target;

	public TargetSource(Object target) {
		this.target = target;
	}

	public Class<?>[] getTargetClass() {
		return this.target.getClass().getInterfaces();
	}

	public Object getTarget() {
		return this.target;
	}
}
