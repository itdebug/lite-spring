package com.itdebug.springframework.test;

import com.itdebug.springframework.PropertyValue;
import com.itdebug.springframework.PropertyValues;
import com.itdebug.springframework.factory.entity.BeanDefinition;
import com.itdebug.springframework.factory.entity.BeanReference;
import com.itdebug.springframework.factory.support.DefaultListableBeanFactory;
import com.itdebug.springframework.test.bean.UserDao;
import com.itdebug.springframework.test.bean.UserService;
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
        //2.UserService 设置属性[uId、userDao]
        BeanDefinition beanDefinition = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao", beanDefinition);

        //3.UserService 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        //4.UserService 注入Bean
        BeanDefinition definition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", definition);

        //5.UserService 获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.getUser();
    }
}
