package com.itdebug.springframework.context.annonation;

import cn.hutool.core.util.ClassUtil;
import com.itdebug.springframework.beans.factory.entity.BeanDefinition;
import com.itdebug.springframework.steretype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/24
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class ClassPathScanningCandidateComponentProvider {

	public Set<BeanDefinition> findCandidateComponents(String basePackage) {
		Set<BeanDefinition> candidates = new LinkedHashSet<BeanDefinition>();
		// 扫描有org.springframework.stereotype.Component注解的类
		Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
		for (Class<?> clazz : classes) {
			BeanDefinition beanDefinition = new BeanDefinition(clazz);
			candidates.add(beanDefinition);
		}
		return candidates;
	}
}
