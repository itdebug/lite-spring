package com.itdebug.springframework.io;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/8
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface ResourceLoader {

	String CLASSPATH_URL_PREFIX = "classpath:";

	Resource getResource(String location);
}
