package com.itdebug.springframework.beans.factory;

import com.itdebug.springframework.beans.factory.exception.SpringBeansException;
import java.util.Map;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/8
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 类型
     *
     * @param type
     * @param <T>
     * @return
     * @throws SpringBeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws SpringBeansException;

    /**
     * 返回注册表中所有Bean的名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}
