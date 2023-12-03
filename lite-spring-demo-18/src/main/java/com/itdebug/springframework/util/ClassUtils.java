package com.itdebug.springframework.util;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/8
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class ClassUtils {

	public static ClassLoader getDefaultClassLoader() {
		ClassLoader classLoader = null;
		try {
			classLoader = Thread.currentThread().getContextClassLoader();
		} catch (Exception e) {
		}

		if (classLoader == null) {
			classLoader = ClassUtils.class.getClassLoader();
		}
		return classLoader;
	}

	/**
	 * Check whether the specified class is a CGLIB-generated class.
	 *
	 * @param clazz the class to check
	 */
	public static boolean isCglibProxyClass(Class<?> clazz) {
		return (clazz != null && isCglibProxyClassName(clazz.getName()));
	}

	/**
	 * Check whether the specified class name is a CGLIB-generated class.
	 *
	 * @param className the class name to check
	 */
	public static boolean isCglibProxyClassName(String className) {
		return (className != null && className.contains("$$"));
	}
}
