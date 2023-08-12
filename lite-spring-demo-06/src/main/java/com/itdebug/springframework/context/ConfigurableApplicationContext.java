package com.itdebug.springframework.context;

import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/12
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     *
     * @throws SpringBeansException
     */
    void refresh() throws SpringBeansException;
}
