package com.itdebug.springframework.beans.factory;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/13
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
