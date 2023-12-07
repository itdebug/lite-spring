package com.itdebug.springframework.beans.factory;

import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/12/6
 * @描述
 */
public interface ObjectFactory<T> {

	T getObject() throws SpringBeansException;
}
