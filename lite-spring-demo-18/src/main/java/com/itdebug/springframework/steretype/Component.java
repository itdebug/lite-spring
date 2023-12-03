package com.itdebug.springframework.steretype;

import java.lang.annotation.*;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/24
 * @地址 https://github.com/itdebug/
 * @描述
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

	String value() default "";
}
