package com.itdebug.springframework.exception;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class SpringBeansException extends RuntimeException {

    public SpringBeansException(String message) {
        super(message);
    }

    public SpringBeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
