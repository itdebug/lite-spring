package com.itdebug.springframework.context.event;

import com.itdebug.springframework.context.ApplicationEvent;
import com.itdebug.springframework.context.ApplicationListener;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/13
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
