package com.itdebug.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/11/28
 * @描述
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Inherited
@Documented
public @interface Qualifier {

	String value() default "";

}
