package com.itdebug.springframework.context.event;

import com.itdebug.springframework.context.ApplicationContext;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/13
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class ContextClosedEvent extends ApplicationContextEvent {

	public ContextClosedEvent(ApplicationContext source) {
		super(source);
	}
}
