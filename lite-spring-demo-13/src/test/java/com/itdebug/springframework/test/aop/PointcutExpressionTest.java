package com.itdebug.springframework.test.aop;

import com.itdebug.springframework.aop.aspectj.AspectJExpressionPointCut;
import com.itdebug.springframework.test.service.SchoolService;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/15
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class PointcutExpressionTest {

    @Test
    public void testPointcutExpression() throws Exception {
        AspectJExpressionPointCut pointcut = new AspectJExpressionPointCut("execution(* com.itdebug.springframework.test.service.SchoolService.*(..))");
        Class<SchoolService> clazz = SchoolService.class;
        Method method = clazz.getDeclaredMethod("getSchool");
        Method getBeanFactory = clazz.getDeclaredMethod("getBeanFactory");
        Method getApplicationContext = clazz.getDeclaredMethod("getApplicationContext");

        assertThat(pointcut.matches(clazz)).isTrue();
        assertThat(pointcut.matches(method, clazz)).isTrue();
        assertThat(pointcut.matches(getBeanFactory, clazz)).isTrue();
        assertThat(pointcut.matches(getApplicationContext, clazz)).isTrue();
    }
}
