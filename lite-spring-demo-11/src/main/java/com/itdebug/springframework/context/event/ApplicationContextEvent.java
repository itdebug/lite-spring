package com.itdebug.springframework.context.event;

import com.itdebug.springframework.context.ApplicationEvent;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/13
 * @地址 https://github.com/itdebug/
 * @描述
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationEvent getApplicationEvent() {
        return (ApplicationEvent) getSource();
    }
}
