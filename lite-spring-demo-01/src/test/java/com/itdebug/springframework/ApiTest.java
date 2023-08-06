package com.itdebug.springframework;

import com.itdebug.springframework.bean.UserService;
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
        String beanName = "userService";
        //1.初始化 BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        //2.注入bean: 定义bean的元数据信息
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
        //3.获取bean：从工厂类中获取对象
        UserService userService = (UserService) beanFactory.getBean(beanName);
        userService.getUser();
    }
}
