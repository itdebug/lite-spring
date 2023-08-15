package com.itdebug.springframework.test.config.event;


import com.itdebug.springframework.context.ApplicationListener;
import com.itdebug.springframework.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
