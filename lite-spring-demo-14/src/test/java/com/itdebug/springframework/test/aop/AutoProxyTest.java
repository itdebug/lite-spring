package com.itdebug.springframework.test.aop;

import com.itdebug.springframework.context.support.ClassPathXmlApplicationContext;
import com.itdebug.springframework.test.service.UserService;
import org.junit.Test;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/23
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class AutoProxyTest {

    @Test
    public void testAutoProxy() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.location();
    }
}
