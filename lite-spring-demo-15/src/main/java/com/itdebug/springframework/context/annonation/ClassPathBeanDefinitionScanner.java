package com.itdebug.springframework.context.annonation;

import cn.hutool.core.util.StrUtil;
import com.itdebug.springframework.beans.factory.entity.BeanDefinition;
import com.itdebug.springframework.beans.factory.registry.BeanDefinitionRegistry;
import com.itdebug.springframework.steretype.Component;

import java.util.Set;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/24
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

	private BeanDefinitionRegistry registry;

	public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}

	public void doScan(String... basePackages) {
		for (String basePackage : basePackages) {
			Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
			for (BeanDefinition candidate : candidates) {
				// 解析bean的作用域
				String beanScope = resolveBeanScope(candidate);
				if (StrUtil.isNotEmpty(beanScope)) {
					candidate.setScope(beanScope);
				}
				//生成bean的名称
				String beanName = determineBeanName(candidate);
				//注册BeanDefinition
				registry.registerBeanDefinition(beanName, candidate);
			}
		}
	}

	/**
	 * 获取bean的作用域
	 *
	 * @param beanDefinition
	 * @return
	 */
	private String resolveBeanScope(BeanDefinition beanDefinition) {
		Class<?> beanClass = beanDefinition.getBeanClass();
		Scope scope = beanClass.getAnnotation(Scope.class);
		if (scope != null) {
			return scope.value();
		}

		return StrUtil.EMPTY;
	}

	/**
	 * 生成bean的名称
	 *
	 * @param beanDefinition
	 * @return
	 */
	private String determineBeanName(BeanDefinition beanDefinition) {
		Class<?> beanClass = beanDefinition.getBeanClass();
		Component component = beanClass.getAnnotation(Component.class);
		String value = component.value();
		if (StrUtil.isEmpty(value)) {
			value = StrUtil.lowerFirst(beanClass.getSimpleName());
		}
		return value;
	}

}
