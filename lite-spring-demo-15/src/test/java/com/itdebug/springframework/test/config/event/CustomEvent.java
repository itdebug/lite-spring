package com.itdebug.springframework.test.config.event;


import com.itdebug.springframework.context.ApplicationContext;
import com.itdebug.springframework.context.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {

    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
