package com.itdebug.springframework.test.config.event;


import com.itdebug.springframework.context.ApplicationListener;
import com.itdebug.springframework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		System.out.println(this.getClass().getName());
	}
}
