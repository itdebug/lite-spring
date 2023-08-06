package com.itdebug.springframework;

import com.itdebug.springframework.exception.SpringBeansException;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface BeanFactory {

    Object getBean(String name) throws SpringBeansException;
}
