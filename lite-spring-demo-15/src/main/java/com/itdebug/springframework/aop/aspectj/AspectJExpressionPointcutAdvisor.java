package com.itdebug.springframework.aop.aspectj;

import com.itdebug.springframework.aop.PointCut;
import com.itdebug.springframework.aop.framework.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * Advisor是包含一个Pointcut和一个Advice的组合，
 * Pointcut用于捕获JoinPoint，Advice决定在JoinPoint执行某种操作。实现了一个支持aspectj表达式的AspectJExpressionPointcutAdvisor。
 * @创建人 Eric.Lu
 * @创建时间 2023/8/23
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointCut pointcut;

    private Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public PointCut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectJExpressionPointCut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
