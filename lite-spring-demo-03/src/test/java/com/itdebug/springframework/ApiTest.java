package com.itdebug.springframework;

import com.itdebug.springframework.bean.UserService;
import com.itdebug.springframework.entity.BeanDefinition;
import com.itdebug.springframework.support.DefaultListableBeanFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
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

    @Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override public int hashCode() {
                return super.hashCode();
            }
        });
        Object object = enhancer.create(new Class[] {String.class}, new Object[] {"Eric.Lu"});
        System.out.println(object);
    }

    @Test
    public void test_jdk() throws InstantiationException, IllegalAccessException {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
        userService.getUser();
    }

    @Test
    public void test_jdk_constructor() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("Eric.Lu");
        System.out.println(userService);
    }

    @Test
    public void test_parameterTypes() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<?>[] declaredConstructors = userServiceClass.getDeclaredConstructors();
        Constructor<?> realConstructor = null;
        for (Constructor<?> constructor : declaredConstructors) {
            if (constructor.getParameterTypes().length == 1) {
                realConstructor = constructor;
                break;
            }
        }

        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(realConstructor.getParameterTypes());
        UserService userService = declaredConstructor.newInstance("Eric.Lu");
        System.out.println(userService);
    }
}
