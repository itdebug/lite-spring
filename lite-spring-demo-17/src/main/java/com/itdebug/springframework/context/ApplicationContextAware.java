package com.itdebug.springframework.context;

import com.itdebug.springframework.beans.factory.Aware;
import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/13
 * @地址 https://github.com/itdebug/
 * @描述 标记类接口，用于感知到类类型，设置bean工厂
 */
public interface ApplicationContextAware extends Aware {

	void setApplicationContext(ApplicationContext applicationContext) throws SpringBeansException;
}
