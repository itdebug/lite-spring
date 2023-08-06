package com.itdebug.springframework;

import com.itdebug.springframework.bean.UserService;
import com.itdebug.springframework.entity.BeanDefinition;
import com.itdebug.springframework.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 <a href="https://github.com/itdebug/">...</a>
 * @描述
 */
public class ApiTest {

    @Test
    public void test_bean_factory() {
        //1.初始化Bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //2.注册Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        //3.获取Bean
        UserService userService_singleton = (UserService) beanFactory.getBean("userService", "Eric.Lu");
        System.out.println(userService_singleton.toString());
    }
}
